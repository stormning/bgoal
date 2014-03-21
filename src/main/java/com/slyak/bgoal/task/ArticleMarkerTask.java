/**
 * Project name : bgoal
 * File name : ArticleMarkerTask.java
 * Package name : com.slyak.bgoal.task
 * Date : 2013年11月27日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;

import com.slyak.bgoal.enums.Status;
import com.slyak.bgoal.model.Source;
import com.slyak.bgoal.service.SourceService;
import com.slyak.bgoal.service.Spider;

public class ArticleMarkerTask {
	
	private ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	@Autowired
	private SourceService sourceService;
	
	@Autowired
	private Spider spider;
	
	public void make(){
		List<Source> sources = sourceService.findByStatus(Status.ENABLED);
		for (final Source source : sources) {
			Runnable runnable = new Runnable() {
				public void run() {
					spider.fetch(source);
				}
			};
			executorService.execute(runnable);
		}
	}
}
