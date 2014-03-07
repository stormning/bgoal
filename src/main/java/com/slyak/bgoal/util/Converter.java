/**
 * Project name : bgoal
 * File name : Converter.java
 * Package name : com.slyak.bgoal.util
 * Date : 2014年1月15日
 * Copyright : 2014 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import com.slyak.bgoal.model.Article;
import com.slyak.bgoal.model.SearchResult;

public class Converter {
	public static Article toArticle(SearchResult<String, Object> searchResult){
		Article article = new Article();
		article.setId((Long)searchResult.get("id"));
		article.setCreateAt(new Date((Long)searchResult.get("createAt")));
		article.setCreator((Long)searchResult.get("creator"));
		article.setTitle((String)searchResult.get("title"));
		article.setSourceId((Long)searchResult.get("sourceId"));
		return article;
	}
	
	public static Page<Article> toArticlePage(Page<SearchResult<String, Object>> searchResultPage){
		Pageable pageable = new PageRequest(searchResultPage.getNumber(), searchResultPage.getSize());
		return new PageImpl<Article>(toArticles(searchResultPage.getContent()), pageable , searchResultPage.getTotalPages());
	}
	
	public static List<Article> toArticles(List<SearchResult<String, Object>> searchResults){
		if(!CollectionUtils.isEmpty(searchResults)){
			List<Article> articles = new ArrayList<Article>();
			for (SearchResult<String, Object> searchResult : searchResults) {
				articles.add(toArticle(searchResult));
			}
			return articles;
		}
		return Collections.emptyList();
	}
}
