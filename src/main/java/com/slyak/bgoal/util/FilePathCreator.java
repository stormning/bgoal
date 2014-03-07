/**
 * Project name : bgoal
 * File name : FilePathCreator.java
 * Package name : com.slyak.bgoal.util
 * Date : 2013年12月20日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.util;

import java.util.List;

import com.slyak.core.util.StringUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class FilePathCreator implements TemplateMethodModelEx {

	@Override
	public Object exec(List arguments) throws TemplateModelException {
		//0 biz 1 owner 2 imgname
		return "/file/"+arguments.get(0)+StringUtils.devidePath(String.valueOf(arguments.get(1)), "/")+"/"+arguments.get(2)+".jpg";
	}

}
