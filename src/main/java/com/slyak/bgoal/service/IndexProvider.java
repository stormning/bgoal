/**
 * Project name : bgoal
 * File name : IndexService.java
 * Package name : com.slyak.bgoal.service
 * Date : 2013年12月5日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service;

import java.io.Serializable;

public interface IndexProvider<T,ID extends Serializable> {
	
	void remove(ID id);
	
	void update(ID id);
	
	void create(T entity);
	
	long count();
	
	void rebuildAll();
}
