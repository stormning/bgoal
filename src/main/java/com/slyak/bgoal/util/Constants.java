/**
 * Project name : bgoal
 * File name : Constants.java
 * Package name : com.slyak.bgoal.util
 * Date : 2013年12月5日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.util;

import java.util.Collections;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@SuppressWarnings({"unchecked","rawtypes"})
public class Constants {
	
	private static final Page EMPTY_PAGE = new PageImpl(Collections.emptyList());
	
	public static final <T> Page<T> emptyPage(){
		return (Page<T>)EMPTY_PAGE;
	}
	public static final String FIELD_ID = "_ID_";
	public static final String FIELD_TYPE = "_TYPE_";
	public static final String FIELD_SEARCH = "_SEARCH_";
	public static final class TYPES {
		public static final Integer SOURCE = 100;
		public static final Integer ARTICLE = 0;
	}
}