/**
 * Project name : bgoal
 * File name : SearchResult.java
 * Package name : com.slyak.bgoal.model
 * Date : 2013年12月10日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.model;

import java.io.Serializable;
import java.util.HashMap;

import com.slyak.bgoal.service.Typeable;

public class SearchResult<K, V> extends HashMap<K, V> implements Serializable,Typeable{

	private static final long serialVersionUID = 1L;

	private int type;

	@Override
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
