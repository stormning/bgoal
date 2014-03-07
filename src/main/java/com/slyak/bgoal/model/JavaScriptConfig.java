/**
 * Project name : bgoal
 * File name : JavaScriptConfig.java
 * Package name : com.slyak.bgoal.util
 * Date : 2013年12月23日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.model;

public class JavaScriptConfig {
	
	private String detailUrlsFunction;
	
	private String detailTitleFunction;
	
	private String detailContentFunction;
	
	private String nextPageUrlFunction;
	
	public void setDetailUrlsFunction(String detailUrlsFunction) {
		this.detailUrlsFunction = detailUrlsFunction;
	}

	public void setDetailTitleFunction(String detailTitleFunction) {
		this.detailTitleFunction = detailTitleFunction;
	}

	public void setDetailContentFunction(String detailContentFunction) {
		this.detailContentFunction = detailContentFunction;
	}

	public void setNextPageUrlFunction(String nextPageUrlFunction) {
		this.nextPageUrlFunction = nextPageUrlFunction;
	}

	/**
	 * @return
	 */
	public String getDetailUrlsFunction() {
		return detailUrlsFunction;
	}

	/**
	 * @return
	 */
	public String getDetailTitleFunction() {
		return detailTitleFunction;
	}
	
	/**
	 * @return
	 */
	public String getDetailContentFunction() {
		return detailContentFunction;
	}
	
	/**
	 * @return
	 */
	public String getNextPageUrlFunction() {
		return nextPageUrlFunction;
	}
}
