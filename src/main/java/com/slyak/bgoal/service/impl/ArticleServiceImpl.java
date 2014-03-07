/**
 * Project name : bgoal
 * File name : ArticleServiceImpl.java
 * Package name : com.slyak.bgoal.service.impl
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import com.slyak.bgoal.dao.ArticleDao;
import com.slyak.bgoal.dao.SpecificDao;
import com.slyak.bgoal.enums.ArticleStatus;
import com.slyak.bgoal.model.Article;
import com.slyak.bgoal.model.IndexField;
import com.slyak.bgoal.model.SearchResult;
import com.slyak.bgoal.model.Source;
import com.slyak.bgoal.service.ArticleService;
import com.slyak.bgoal.service.IndexFieldProvider;
import com.slyak.bgoal.service.SourceService;
import com.slyak.bgoal.service.TemplateRender;
import com.slyak.bgoal.util.Constants;
import com.slyak.core.util.StringUtils;
import com.slyak.user.service.UserService;

@Service
public class ArticleServiceImpl extends AbstractIndexProvider<Article, Long> implements ArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private SourceService sourceService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SpecificDao specificDao;
	
	@Autowired
	private TemplateRender templateRender;

	@Override
	public void saveArticle(Article article) {
		Article old = null;
		if(article.getId()!=null){
			old = articleDao.findOne(article.getId());
		} else{
			String oldUrl = article.getOldUrl();
			if(!StringUtils.isEmpty(oldUrl)) {
				String oldUrlShort = shortMD5(oldUrl);
				article.setOldUrlShort(oldUrlShort);
				old = articleDao.findByOldUrlShort(oldUrlShort);
			}
			if (article.getCreateAt() == null) {
				article.setCreateAt(new Date());
			}
		}
		if(old == null){
			articleDao.save(article);
			create(article);
		} else{
			old.setContent(article.getContent());
			old.setTitle(article.getTitle());
			if(article.getCreator()!=null) {
				old.setCreator(article.getCreator());
			}
			update(article.getId());
		}
	}

	@Override
	public void removeExistUrls(Set<String> urls) {
		Set<String> md5s = new HashSet<String>();
		for (String url : urls) {
			md5s.add(shortMD5(url));
		}
		List<Article> articles = articleDao.findByOldUrlShortIn(md5s);
		if(!CollectionUtils.isEmpty(articles)){
			for (Article article : articles) {
				urls.remove(article.getOldUrl());
			}
		}
	}

	
	private String shortMD5(String url){
		StringBuilder sb = new StringBuilder();
		DigestUtils.appendMd5DigestAsHex(url.getBytes(),sb);
		return sb.substring(9,25);
	}
	
	
	@Override
	public String renderTimelines(List<SearchResult<String, Object>> content) {
		StringBuffer stringBuffer  = new StringBuffer();
		int sz = 0;
		if(content!=null && (sz = content.size())>0){
			for (int i = 0; i < sz; i++) {
				SearchResult<String, Object> searchResult = content.get(i);
				stringBuffer.append(renderTimeline(searchResult));
			}
		}
		return stringBuffer.toString();
	}

	private String renderTimeline(SearchResult<String, Object> searchResult) {
		return templateRender.render(searchResult.getType(),searchResult);
	}

	@Override
	public List<IndexFieldProvider<Article>> getIndexFieldProviders(final Article entity) {
		IndexFieldProvider<Article> provider = new IndexFieldProvider<Article>() {
			@Override
			public List<IndexField> generateInfoFields(Article article) {
				List<IndexField> fields = new ArrayList<IndexField>();
				fields.add(new IndexField("id", entity.getId()));
				fields.add(new IndexField("title", entity.getTitle(),true));
				fields.add(new IndexField("content", StringUtils.cut(entity.getContent(), 300)));
				fields.add(new IndexField("createAt", entity.getCreateAt()));
				Long creator = entity.getCreator();
				if(creator!=null){
					fields.add(new IndexField("creator", creator));
					fields.add(new IndexField("creatorName", userService.getUser(creator).getNickName()));
				}
				fields.add(new IndexField("imgCount", entity.getImgCount()));
				
				Long sourceId = entity.getSourceId();
				if(sourceId!=null) {
					Source source = sourceService.findOne(sourceId);
					fields.add(new IndexField("sourceId", entity.getSourceId()));
					fields.add(new IndexField("sourceName", source.getName()));
				}
				fields.add(new IndexField("status", entity.getStatus()));
				return fields;
			}

			@Override
			public int getType() {
				return Constants.TYPES.ARTICLE;
			}

			@Override
			public String generateSearchArea(Article entity) {
				StringBuffer sBuffer = new StringBuffer();
				Long sourceId = entity.getSourceId();
				if(sourceId!=null) {
					sBuffer.append(sourceService.findOne(entity.getSourceId()).getName());
				}
				sBuffer.append(entity.getTitle());
				sBuffer.append(StringUtils.cleanHtml(entity.getContent()));
				return sBuffer.toString();
			}
		};
		return Collections.singletonList(provider);
	}

	/* (non-Javadoc)
	 * @see com.slyak.bgoal.service.ArticleService#findOne(java.lang.Long)
	 */
	@Override
	public Article findOne(Long articleId) {
		return articleDao.findOne(articleId);
	}

	/* (non-Javadoc)
	 * @see com.slyak.bgoal.service.ArticleService#findAll(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Article> findAll(Pageable pageable) {
		return articleDao.findAll(pageable);
	}

	/* (non-Javadoc)
	 * @see com.slyak.bgoal.service.ArticleService#getUsersArticleCount(java.util.List)
	 */
	@Override
	public Map<Long, Long> getUsersArticleCount(List<Long> userIds) {
		return specificDao.getUsersArticleCount(userIds);
	}

	/* (non-Javadoc)
	 * @see com.slyak.bgoal.service.ArticleService#toggleStatus(java.lang.Long)
	 */
	@Override
	public void toggleStatus(Long articleId) {
		Article article = articleDao.findOne(articleId);
		article.setStatus(ArticleStatus.values()[1-article.getStatus().ordinal()]);
		articleDao.save(article);
		update(article.getId());
	}
}
