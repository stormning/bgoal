/**
 * Project name : bgoal
 * File name : SpecificDao.java
 * Package name : com.slyak.bgoal.dao
 * Date : 2013年12月24日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.dao;

import java.util.List;
import java.util.Map;

import com.slyak.bgoal.model.SourceStatistics;

public interface SpecificDao {

	Map<Long, SourceStatistics> getSourceStatistics();

	Map<Long, Long> getUsersArticleCount(List<Long> userIds); 
}
