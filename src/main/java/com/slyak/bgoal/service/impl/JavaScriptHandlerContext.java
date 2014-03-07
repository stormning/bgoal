/**
 * Project name : bgoal
 * File name : JavaScriptHandlerContext.java
 * Package name : com.slyak.bgoal.service.impl
 * Date : 2013年12月23日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.mozilla.javascript.NativeArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.slyak.bgoal.model.JavaScriptConfig;
import com.slyak.bgoal.model.RuleType;
import com.slyak.bgoal.service.HandlerContext;
import com.slyak.bgoal.service.SourceHandler;
import com.slyak.core.js.JavaScriptHandler;

@Component("javaScriptHandlerContext")
public class JavaScriptHandlerContext extends DefaultHandlerContext implements HandlerContext{
	
	@Autowired
	private JavaScriptHandler javaScriptHandler;
	
	/* (non-Javadoc)
	 * @see com.slyak.bgoal.service.HandlerContext#supports(com.slyak.bgoal.model.RuleType)
	 */
	@Override
	public boolean supports(RuleType ruleType) {
		return RuleType.JAVASCRIPT == ruleType;
	}

	/* (non-Javadoc)
	 * @see com.slyak.bgoal.service.impl.CacheableHandlerContext#generateSourceHandler(java.lang.String)
	 */
	@Override
	protected SourceHandler generateSourceHandler(String jsonConfig) {
		JavaScriptConfig javaScriptConfig = JSON.parseObject(jsonConfig,JavaScriptConfig.class);
		return new JavaScriptArticleSourceHandler(javaScriptConfig);
	}
	
	public class JavaScriptArticleSourceHandler extends DefaultArticleSourceHandler {
		private JavaScriptConfig javaScriptConfig;
		
		public JavaScriptArticleSourceHandler(JavaScriptConfig javaScriptConfig){
			this.javaScriptConfig = javaScriptConfig;
		}
		
		@Override
		protected List<String> getDetalUrls(Document document){
			NativeArray result = (NativeArray) javaScriptHandler.evalFunction(javaScriptConfig.getDetailUrlsFunction(), document.html());
			List<String> urls = new ArrayList<String>();
			for (Object cs : result) {
				urls.add(cs.toString());
			}
			return urls;
		}

		@Override
		protected String getNextPageUrl(Element element) {
			Object o = javaScriptHandler.evalFunction(javaScriptConfig.getNextPageUrlFunction(), element.html());
			if(o!=null){
				return o.toString();
			}
			return null;
		}

		@Override
		protected String getDetailTitle(Element element) {
			Object o = javaScriptHandler.evalFunction(javaScriptConfig.getDetailTitleFunction(), element.html());
			if(o!=null){
				return o.toString();
			}
			return null;
		}

		@Override
		protected String getDetailContent(Element element) {
			Object o = javaScriptHandler.evalFunction(javaScriptConfig.getDetailContentFunction(), element.html());
			if(o!=null){
				return o.toString();
			}
			return null;
		}
	}
}
