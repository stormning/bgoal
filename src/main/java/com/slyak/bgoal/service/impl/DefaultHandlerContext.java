/**
 * Project name : bgoal
 * File name : CacheableHandlerContext.java
 * Package name : com.slyak.bgoal.service.impl
 * Date : 2013年12月23日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.slyak.bgoal.service.HandlerContext;
import com.slyak.bgoal.service.SourceHandler;

public abstract class DefaultHandlerContext implements HandlerContext,ApplicationContextAware {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHandlerContext.class);
	
	private Map<Long, SourceHandler> ruleHandlers = new ConcurrentHashMap<Long, SourceHandler>();

	private ApplicationContext applicationContext;
	

	/* (non-Javadoc)
	 * @see com.slyak.bgoal.service.HandlerContext#getSourceHandler(java.lang.Long, java.lang.String)
	 */
	@Override
	public SourceHandler getSourceHandler(Long sourceId, String jsonConfig) {
		SourceHandler sourceHandler = ruleHandlers.get(sourceId);
		if (sourceHandler == null) {
			try {
				sourceHandler = generateSourceHandler(jsonConfig);
				applicationContext.getAutowireCapableBeanFactory().autowireBean(sourceHandler);
				ruleHandlers.put(sourceId, sourceHandler);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return sourceHandler;
	}
	
	abstract SourceHandler generateSourceHandler(String jsonConfig);

	public void clear(Long sourceId) {
		ruleHandlers.remove(sourceId);
	}

	public void clearAll() {
		ruleHandlers.clear();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
