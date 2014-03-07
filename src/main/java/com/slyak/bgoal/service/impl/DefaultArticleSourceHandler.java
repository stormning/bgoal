/**
 * Project name : bgoal
 * File name : DefaultSourceHandler.java
 * Package name : com.slyak.bgoal.service.impl
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.slyak.bgoal.enums.ArticleStatus;
import com.slyak.bgoal.model.Article;
import com.slyak.bgoal.model.Source;
import com.slyak.bgoal.service.ArticleService;
import com.slyak.bgoal.service.SourceHandler;

public class DefaultArticleSourceHandler implements SourceHandler {
	
	private static final String USER_AGENT_FIREFOX="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36";
	
	@Autowired
	public ArticleService articleService;
	
	private String nextPageUrlQuery;
	private String detailUrlQuery;
	private String detailTitleQuery;
	private String detailContentQuery;
	
	@Override
	public void handle(Source source) {
		Document document = getDocument(source.getUrl());
		if(document!=null){
			//list article urls
			List<String> newUrls = getDetalUrls(document);
			if(!StringUtils.isEmpty(newUrls)){
				Set<String> noRepeatUrls = new HashSet<String>(newUrls);
				articleService.removeExistUrls(noRepeatUrls);
				for(String url : noRepeatUrls){
					if(!StringUtils.isEmpty(url)){
						Document ad = getDocument(url);
						buildArticle(source, ad ,url);
					}
				}
				String nextPageUrl = null;
				if(newUrls.size() == noRepeatUrls.size() && !StringUtils.isEmpty(nextPageUrl=getNextPageUrl(document))){
					Source nextSource = new Source();
					nextSource.setId(source.getId());
					nextSource.setUrl(nextPageUrl);
					handle(nextSource);
				}
			}
		}
	}
	
	protected List<String> getDetalUrls(Document document){
		Elements listElements = document.select(getDetailUrlQuery());
		if(!listElements.isEmpty()){
			return new ArrayList<String>(findNewUrls(listElements));
		}
		return Collections.emptyList();
	} 
	
	private Set<String> findNewUrls(Elements elements){
		Set<String> urls = new LinkedHashSet<String>();
		for (Element element : elements) {
			urls.add(element.absUrl("href"));
		}
		return urls;
	}
	
	protected Document getDocument(String url){
		try {
			return Jsoup.connect(url).userAgent(DefaultArticleSourceHandler.USER_AGENT_FIREFOX).header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	protected String getNextPageUrl(Element element) {
		if(StringUtils.isEmpty(getNextPageUrlQuery())){
			return null;
		}
		return element.select(getNextPageUrlQuery()).get(0).absUrl("href");
	}

	protected void buildArticle(Source source, Element element ,String url) {
		try{
			Article article = new Article();
			article.setContent(getDetailContent(element));
			article.setSourceId(source.getId());
			article.setTitle(getDetailTitle(element));
			article.setOldUrl(url);
			article.setCreateAt(new Date());
			article.setStatus(ArticleStatus.NORMAL);
			articleService.saveArticle(article);
		} catch (Exception e){
			//ignore
			e.printStackTrace();
		}
	}

	
	protected String getDetailTitle(Element element) {
		return element.select(getDetailTitleQuery()).get(0).text();
	}

	protected String getDetailContent(Element element) {
		return element.select(getDetailContentQuery()).get(0).outerHtml();
	}

	protected String getNextPageUrlQuery(){
		return nextPageUrlQuery;
	}

	protected String getDetailUrlQuery(){
		return detailUrlQuery;
	}

	protected String getDetailTitleQuery(){
		return detailTitleQuery;
	}

	protected String getDetailContentQuery(){
		return detailContentQuery;
	}

	public static void main(String[] args) {
		System.out.println(Jsoup.parse("<a href='http://www.baidu.com'>百度</a><a>xxx</a>").select("a").outerHtml());
	}
	
}
