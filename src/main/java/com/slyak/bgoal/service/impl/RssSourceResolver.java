/**
 * Project name : bgoal
 * File name : RssSourceResolver.java
 * Package name : com.slyak.bgoal.service.impl
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service.impl;

import com.slyak.bgoal.enums.SourceType;
import com.slyak.bgoal.model.Source;
import com.slyak.bgoal.service.SourceResolver;

public class RssSourceResolver implements SourceResolver {

	@Override
	public boolean support(SourceType type) {
		return type == SourceType.RSS;
	}

	@Override
	public void handle(Source source) {
		// TODO Auto-generated method stub
	}

}
