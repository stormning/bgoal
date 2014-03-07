/**
 * Project name : bgoal
 * File name : GroovyConfig.java
 * Package name : com.slyak.bgoal.model
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.model;

import java.util.List;

public class GroovyConfig {
	private List<String> imports;
	private String content;
	public List<String> getImports() {
		return imports;
	}
	public void setImports(List<String> imports) {
		this.imports = imports;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
