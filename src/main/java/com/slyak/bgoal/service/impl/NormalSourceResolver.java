/**
 * Project name : bgoal
 * File name : CustomizeSourceResolver.java
 * Package name : com.slyak.bgoal.service.impl
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service.impl;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import com.slyak.bgoal.enums.SourceType;
import com.slyak.bgoal.model.RuleType;
import com.slyak.bgoal.model.Source;
import com.slyak.bgoal.model.SourceRule;
import com.slyak.bgoal.service.HandlerContext;
import com.slyak.bgoal.service.SourceHandler;
import com.slyak.bgoal.service.SourceResolver;
import com.slyak.bgoal.service.SourceService;

public class NormalSourceResolver implements SourceResolver, ApplicationContextAware {

	@Autowired
	private SourceService sourceService;
	
	private static final SourceHandler DEFAULT_SOURCE_HANDLER = new DefaultArticleSourceHandler();

	private SourceHandler sourceHandler = DEFAULT_SOURCE_HANDLER;

	private ApplicationContext applicationContext;
	
	@Override
	public boolean support(SourceType type) {
		return type == SourceType.NORMAL;
	}

	@Override
	public void handle(Source source) {
		Long sourceId = source.getId();
		SourceRule sourceRule = sourceService.getSourceRule(sourceId);
		if (sourceRule != null) {
			HandlerContext handlerContext = getHandlerContext(sourceRule.getType());
			if (handlerContext != null) {
				SourceHandler handler = handlerContext.getSourceHandler(sourceId, sourceRule.getRule());
				if (handler != null) {
					this.sourceHandler = handler;
				}
			}
		}
		sourceHandler.handle(source);
	}

	private HandlerContext getHandlerContext(RuleType ruleType) {
		Map<String, HandlerContext> handlerContextsMap = applicationContext.getBeansOfType(HandlerContext.class);
		if(!StringUtils.isEmpty(handlerContextsMap)){
			for (HandlerContext hc : handlerContextsMap.values()) {
				if (hc.supports(ruleType)) {
					return hc;
				}
			}
		}
		return null;
	}

	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void setSourceHandler(SourceHandler sourceHandler) {
		this.sourceHandler = sourceHandler;
	}
}
