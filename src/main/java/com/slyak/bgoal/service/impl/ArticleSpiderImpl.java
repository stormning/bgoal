/**
 * Project name : bgoal
 * File name : ArticleMakerImpl.java
 * Package name : com.slyak.bgoal.service.impl
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service.impl;

import java.util.List;

import com.slyak.bgoal.model.Source;
import com.slyak.bgoal.service.Spider;
import com.slyak.bgoal.service.SourceResolver;

public class ArticleSpiderImpl implements Spider{

	private List<SourceResolver> sourceResolvers;
	
	public void setSourceResolvers(List<SourceResolver> sourceResolvers) {
		this.sourceResolvers = sourceResolvers;
	}

	@Override
	public void fetch(Source source) {
		for(SourceResolver sr : sourceResolvers){
			if(sr.support(source.getType())){
				sr.handle(source);
			}
		}
	}

}
