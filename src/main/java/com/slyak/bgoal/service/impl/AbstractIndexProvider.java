/**
 * Project name : bgoal
 * File name : AbstractEntityIndexService.java
 * Package name : com.slyak.bgoal.service.impl
 * Date : 2013年12月5日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FloatField;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.support.Repositories;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.slyak.bgoal.model.IndexField;
import com.slyak.bgoal.service.IndexFieldProvider;
import com.slyak.bgoal.service.IndexProvider;
import com.slyak.core.lucene.IndexSeacherHelper;

import static com.slyak.bgoal.util.Constants.FIELD_ID;
import static com.slyak.bgoal.util.Constants.FIELD_TYPE;
import static com.slyak.bgoal.util.Constants.FIELD_SEARCH;


public abstract class AbstractIndexProvider<T, ID extends Serializable> implements IndexProvider<T, ID>,ApplicationContextAware {

	private JpaRepository<T, ID> repository;

	@Autowired
	private IndexWriter indexWriter;

	@Autowired
	private Analyzer analyzer;

	@PersistenceContext
	private EntityManager em;
	
	private EntityInformation<T, ID> entityInformation;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		Repositories repositories = new Repositories(applicationContext);
		entityInformation = repositories.getEntityInformationFor((Class<T>) params[0]);
		repository = (JpaRepository)repositories.getRepositoryFor(entityInformation.getJavaType());
	}

	private String getIndexId(Serializable id) {
		return entityInformation.getJavaType().getName() + id.toString();
	}

	@Override
	public void remove(ID id) {
		try {
			Query query = createIdQuery(id);
			if(query!=null){
				indexWriter.deleteDocuments(query);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(indexWriter!=null){
				try {
					indexWriter.commit();
					System.out.println("删除记录"+indexWriter.hasDeletions());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private Query createIdQuery(Serializable id){
		return new TermQuery(new Term(FIELD_ID, getIndexId(id)));
	}

	@Override
	public void update(ID id) {
		List<Document> docs = toDocs(repository.findOne(id));
		if(!CollectionUtils.isEmpty(docs)){
			try {
				indexWriter.updateDocuments(new Term(FIELD_ID, getIndexId(id)), docs);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(indexWriter!=null){
					try {
						indexWriter.commit();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private List<Document> toDocs(T entity) {
		List<IndexFieldProvider<T>> fieldProviders = getIndexFieldProviders(entity);
		if(!CollectionUtils.isEmpty(fieldProviders)){
			List<Document> docs = new ArrayList<Document>();
			for (IndexFieldProvider<T> provider : fieldProviders) {
				List<IndexField> indexFields = provider.generateInfoFields(entity);
				Document doc = new Document();
				doc.add(new TextField(FIELD_SEARCH,provider.generateSearchArea(entity),Store.YES));
				doc.add(new IntField(FIELD_TYPE, provider.getType(), Store.YES));
				doc.add(new StringField(FIELD_ID, getIndexId(entityInformation.getId(entity)), Store.YES));
				for (IndexField field : indexFields) {
					doc.add(createField(field));
				}
				docs.add(doc);
			}
			return docs;
		}
		return Collections.emptyList();
	}

	private IndexableField createField(IndexField field) {
		Object value = field.getValue();
		String name = field.getName();
		Store store = field.isStore() ? Store.YES : Store.NO;
		if (field.isTextField()) {
			return new TextField(name, ObjectUtils.getDisplayString(value), store);
		} else if(value instanceof Date){
			return new LongField(name, ((Date)value).getTime(), store);
		} else if (value instanceof Double) {
			return new DoubleField(name, (Double) value, store);
		} else if (value instanceof Float) {
			return new FloatField(name, (Float) value, store);
		} else if (value instanceof Integer) {
			return new IntField(name, (Integer) value, store);
		} else if (value instanceof Long) {
			return new LongField(name, (Long) value, store);
		} else {
			return new StringField(name, ObjectUtils.getDisplayString(value), store);
		}
	}

	abstract List<IndexFieldProvider<T>> getIndexFieldProviders(T entity);

//	@Override
//	public void create(T entity) {
//		Query query = createIdQuery(entityInformation.getId(entity));
//		if(query!=null){
//			try {
//				TopDocs topDocs = IndexSeacherHelper.getIndexSeacher().search(query, 1);
//				if(topDocs.totalHits == 0){
//					create(entity);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	@Override
	public void create(T entity){
		List<Document> docs = toDocs(entity);
		if(!CollectionUtils.isEmpty(docs)){
			try {
				indexWriter.addDocuments(docs, analyzer);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(indexWriter!=null){
					try {
						indexWriter.commit();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	public long count() {
		return repository.count();
	}
	
	private void deleteAll() {
		try {
			indexWriter.deleteAll();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(indexWriter!=null){
				try {
					indexWriter.commit();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void rebuildAll() {
		deleteAll();
		List<T> entities = repository.findAll();
		for (T t : entities) {
			create(t);
		}
	}

}
