/**
 * Project name : bgoal
 * File name : IndexReaderFactoryBean.java
 * Package name : com.slyak.bgoal.util
 * Date : 2013年12月5日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.util;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.springframework.beans.factory.FactoryBean;

public class IndexReaderFactoryBean implements FactoryBean<IndexReader> {
	
	private Directory directory;
	
	private DirectoryReader directoryReader;
	
	public void setDirectory(Directory directory) {
		this.directory = directory;
	}

	@Override
	public IndexReader getObject() throws Exception {
		if(directoryReader == null){
			directoryReader = DirectoryReader.open(directory);
		}else{
			directoryReader = DirectoryReader.openIfChanged(directoryReader);
		}
		return directoryReader;
	}

	@Override
	public Class<?> getObjectType() {
		return IndexReader.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
