/**
 * Project name : bgoal
 * File name : SearchServiceImpl.java
 * Package name : com.slyak.bgoal.service.impl
 * Date : 2013年12月9日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.slyak.bgoal.model.SearchResult;
import com.slyak.bgoal.service.SearchService;
import com.slyak.bgoal.util.Constants;
import com.slyak.core.lucene.IndexSeacherHelper;

import static com.slyak.bgoal.util.Constants.FIELD_TYPE;

@Service
public class SearchServiceImpl implements SearchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

	@Autowired
	private Analyzer analyzer;

	@Override
	public Page<SearchResult<String, Object>> search(Pageable pageable,Query query) {
		try {
			int offset = pageable.getOffset();
			int pageSize = pageable.getPageSize();
			TopDocs result ;
			if(offset == 0){
				result = IndexSeacherHelper.getIndexSeacher().search(query, pageSize);
			} else {
				TopDocs previous = IndexSeacherHelper.getIndexSeacher().search(query, offset);
				ScoreDoc scoreDoc = previous.scoreDocs[offset-1];
				result = IndexSeacherHelper.getIndexSeacher().searchAfter(scoreDoc, query, pageSize);
			}
			return toPage(result, pageable, result.totalHits);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return Constants.emptyPage();
	}

	/**
	 * @param hits
	 * @param pageable
	 * @param total
	 * @return
	 */
	private PageImpl<SearchResult<String, Object>> toPage(TopDocs hits,
			Pageable pageable, int total) {
		List<SearchResult<String, Object>> content = new ArrayList<SearchResult<String, Object>>();
		int end = Math.min(pageable.getOffset() + pageable.getPageSize(),
				hits.scoreDocs.length);
		try {
			for (int i = 0; i < end; i++) {
				ScoreDoc sdoc = hits.scoreDocs[i];
				Document doc = IndexSeacherHelper.getIndexSeacher().doc(sdoc.doc);
				List<IndexableField> fs = doc.getFields();
				SearchResult<String, Object> item = new SearchResult<String, Object>();
				for (IndexableField f : fs) {
					if (FIELD_TYPE.equals(f.name())) {
						item.setType((f.numericValue()).intValue());
					} else {
						item.put(f.name(), f.stringValue());
					}
				}
				content.add(item);
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return new PageImpl<SearchResult<String, Object>>(content, pageable,
				total);
	}

	public static void main(String[] args) throws IOException, ParseException {
		// F:\opt\bgoal\searcher
		Directory directory = new SimpleFSDirectory(new File(
				"F:\\opt\\bgoal\\searcher"));
		Analyzer analyzer = new IKAnalyzer();
		IndexWriter indexWriter = new IndexWriter(directory,
				new IndexWriterConfig(Version.LUCENE_46, analyzer));
		Document doc = new Document();
		doc.add(new StringField(
				"content",
				"此次培训之后，镇江市将首先开通65所中小学，近5000名教师的网络学习空间进行试点。希望通过“网络学习空间人人通”的建设，能够实现教与学、教与教、学与学全面互动，真正把技术与教学实践的融合落实到每个教师与学生的日常教学活动与学习活动中，帮助镇江市快速完成教育信息化建设指标。",
				Store.YES));
		indexWriter.addDocument(doc);
		indexWriter.close();

		DirectoryReader directoryReader = DirectoryReader.open(directory);
		IndexSearcher is = new IndexSearcher(directoryReader);
		QueryParser parser = new QueryParser(Version.LUCENE_46, "content",
				analyzer);
		TopDocs tds = is.search(parser.parse("日常"), 100);
		System.out.println(tds.scoreDocs.length);
	}

}
