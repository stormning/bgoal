/**
 * Project name : bgoal
 * File name : TemplateRender.java
 * Package name : com.slyak.bgoal.service
 * Date : 2013年12月13日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service;

import java.util.Map;

public interface TemplateRender {

	/**
	 * @param args
	 * @return
	 */
	String render(int type,Map<String, Object> args);

}
