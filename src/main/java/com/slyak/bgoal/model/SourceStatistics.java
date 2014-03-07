/**
 * Project name : bgoal
 * File name : SourceStatistics.java
 * Package name : com.slyak.bgoal.model
 * Date : 2013年12月25日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.model;

import java.util.Date;

public class SourceStatistics {
	
	private long count = 0;
	
	private Date lastUpdate;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
