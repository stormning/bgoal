/**
 * Project name : bgoal
 * File name : GroovySourceHandler.java
 * Package name : com.slyak.bgoal.service.impl
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.slyak.bgoal.model.Source;

/**
 * 所有groovy都继承此类  
 */
public class GroovyArticleSourceHandler extends DefaultArticleSourceHandler {
	
	@Override
	protected String getDetailUrlQuery() {
		return super.getDetailUrlQuery();
	}
	
	@Override
	protected String getDetailTitleQuery() {
		return super.getDetailTitleQuery();
	}

	@Override
	protected String getDetailContentQuery() {
		return super.getDetailContentQuery();
	}
	
	@Override
	protected String getNextPageUrlQuery() {
		return super.getNextPageUrlQuery();
	}
	
	@Override
	public void handle(Source source) {
		super.handle(source);
	}

	@Override
	protected Document getDocument(String url) {
		return super.getDocument(url);
	}

	@Override
	protected String getNextPageUrl(Element element) {
		return super.getNextPageUrl(element);
	}

	@Override
	protected void buildArticle(Source source, Element element,String url) {
		super.buildArticle(source, element,url);
	}

	@Override
	protected String getDetailTitle(Element element) {
		return super.getDetailTitle(element);
	}

	@Override
	protected String getDetailContent(Element element) {
		return super.getDetailContent(element);
	}
}
