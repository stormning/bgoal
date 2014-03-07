/**
 * Project name : bgoal
 * File name : ArticleService.java
 * Package name : com.slyak.bgoal.service
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.slyak.bgoal.model.Article;
import com.slyak.bgoal.model.SearchResult;

public interface ArticleService {
	void saveArticle(Article article);
	void removeExistUrls(Set<String> urls);
	String renderTimelines(List<SearchResult<String, Object>> content);
	Article findOne(Long articleId);
	Page<Article> findAll(Pageable pageable);
	Map<Long, Long> getUsersArticleCount(List<Long> userIds);
	void toggleStatus(Long articleId);
}
