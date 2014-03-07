/**
 * Project name : bgoal
 * File name : FreeMarkerTemplateRender.java
 * Package name : com.slyak.bgoal.service.impl
 * Date : 2013年12月13日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.context.ServletContextAware;

import com.slyak.bgoal.service.TemplateRender;
import com.slyak.bgoal.util.FilePathCreator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerTemplateRender implements TemplateRender,ServletContextAware,InitializingBean {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FreeMarkerTemplateRender.class);
	
	private Map<Integer, String> typeMapping;
	private Configuration configuration;
	private ServletContext servletContext;
	
	public void setTypeMapping(Map<Integer, String> typeMapping) {
		this.typeMapping = typeMapping;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public String render(int type, Map<String, Object> args) {
		try {
			Template template = configuration.getTemplate(typeMapping.get(type));
			args.put("filePath",new FilePathCreator());
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, args);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} catch (TemplateException e) {
			LOGGER.error(e.getMessage());
		}
		return StringUtils.EMPTY;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		configuration.setSharedVariable("ctx", servletContext.getContextPath());
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
