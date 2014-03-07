/**
 * Project name : bgoal
 * File name : IndexFieldProvider.java
 * Package name : com.slyak.bgoal.service
 * Date : 2013年12月6日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service;

import java.util.List;

import com.slyak.bgoal.model.IndexField;

public interface IndexFieldProvider<T> extends Typeable {

	List<IndexField> generateInfoFields(T entity);
	
	String generateSearchArea(T entity);
}
