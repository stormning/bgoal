/**
 * Project name : bgoal
 * File name : GroovyHandlerContext.java
 * Package name : com.slyak.bgoal.service.impl
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service.impl;

import groovy.lang.GroovyClassLoader;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.slyak.bgoal.model.GroovyConfig;
import com.slyak.bgoal.model.RuleType;
import com.slyak.bgoal.service.SourceHandler;

@Component("groovyHandlerContext")
public class GroovyHandlerContext extends DefaultHandlerContext implements ApplicationContextAware {
	private String[] default_imports = {
			"com.slyak.bgoal.service.impl.GroovyArticleSourceHandler",
			"org.jsoup.nodes.Document",
			"org.jsoup.nodes.Element",
			"com.slyak.bgoal.model.Source",
			"org.springframework.beans.factory.annotation.*",
			"com.slyak.bgoal.model.*", "com.slyak.bgoal.service.*",
			"org.jsoup.*" };
	private GroovyClassLoader classLoader = new GroovyClassLoader();
	private AtomicInteger atomicInteger = new AtomicInteger();
	
	@SuppressWarnings("unchecked") 
	SourceHandler generateSourceHandler(String jsonConfig) {
		GroovyConfig groovyConfig = JSON.parseObject(jsonConfig,GroovyConfig.class);
		StringBuffer sb = new StringBuffer();
		for (String imp : default_imports) {
			appendClass(sb, imp);
		}
		List<String> otherImports = groovyConfig.getImports();
		if (!CollectionUtils.isEmpty(otherImports)) {
			for (String imp : otherImports) {
				appendClass(sb, imp);
			}
		}
		sb.append("class ").append("SourceHandler_")
				.append(atomicInteger.incrementAndGet())
				.append(" extends GroovyArticleSourceHandler {\n");
		
//		sb.append("@Autowired\n");
//		sb.append("private ArticleService articleService\n");
		
		sb.append(groovyConfig.getContent());
		sb.append("}\n");
		return createSourceHandler(classLoader.parseClass(sb.toString()));
	}

	protected SourceHandler createSourceHandler(Class<SourceHandler> clazz) {
		return BeanUtils.instantiateClass(clazz);
	}

	private void appendClass(StringBuffer sb, String className) {
		sb.append("import ").append(className).append(";\n");
	}

	@Override
	public boolean supports(RuleType ruleType) {
		return RuleType.GROOVY == ruleType;
	}
}
