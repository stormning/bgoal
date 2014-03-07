/**
 * Project name : bgoal
 * File name : JspTemplateRender.java
 * Package name : com.slyak.bgoal.service.impl
 * Date : 2014年1月17日
 * Copyright : 2014 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.slyak.bgoal.service.TemplateRender;
import com.slyak.core.http.WrappedRequest;
import com.slyak.core.http.WrappedResponse;
import com.slyak.core.web.RequestResponseContextHolder;

public class JspTemplateRender implements TemplateRender {
	
	private Map<Integer, String> typeMapping;
	
	public void setTypeMapping(Map<Integer, String> typeMapping) {
		this.typeMapping = typeMapping;
	}
	
	/* (non-Javadoc)
	 * @see com.slyak.bgoal.service.TemplateRender#render(int, java.util.Map)
	 */
	@Override
	public String render(int type, Map<String, Object> args) {
		HttpServletRequest request = RequestResponseContextHolder.getHttpServletRequest();
		HttpServletResponse response = RequestResponseContextHolder.getHttpServletResponse();
		try {
			WrappedResponse wrappedResponse = new WrappedResponse(response);
			WrappedRequest wrappedRequest = new WrappedRequest(request);
			if(!CollectionUtils.isEmpty(args)){
				for ( Entry<String, Object> entry : args.entrySet()) {
					wrappedRequest.setAttribute(entry.getKey(), entry.getValue());
				}
			}
			request.getRequestDispatcher(typeMapping.get(type)).include(wrappedRequest, wrappedResponse);
			return wrappedResponse.getContent();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return StringUtils.EMPTY;
	}

}
