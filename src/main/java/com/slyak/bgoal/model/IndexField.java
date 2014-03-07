/**
 * Project name : bgoal
 * File name : IndexField.java
 * Package name : com.slyak.bgoal.model
 * Date : 2013年12月6日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.model;

import java.io.Serializable;

public class IndexField implements Serializable{
	private static final long serialVersionUID = 1L;

	private String name;
	
	private Object value;
	
	private boolean textField = false;
	
	private boolean store = true;
	
	@SuppressWarnings("unused")
	private IndexField(){}
	
	public IndexField(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	public IndexField(String name, Object value, boolean textField) {
		this(name,value);
		this.textField = textField;
	}

	public IndexField(String name, Object value, boolean textField,boolean store) {
		this(name,value,textField);
		this.store = store;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public boolean isTextField() {
		return textField;
	}

	public boolean isStore() {
		return store;
	}
}
