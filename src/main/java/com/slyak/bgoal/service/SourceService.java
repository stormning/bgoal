/**
 * Project name : bgoal
 * File name : SourceService.java
 * Package name : com.slyak.bgoal.service
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service;

import java.util.List;
import java.util.Map;

import com.slyak.bgoal.enums.Status;
import com.slyak.bgoal.model.RuleType;
import com.slyak.bgoal.model.Source;
import com.slyak.bgoal.model.SourceRule;
import com.slyak.bgoal.model.SourceStatistics;

public interface SourceService {
	SourceRule getSourceRule(Long sourceId);
	List<Source> findAll();
	List<Source> findByStatus(Status status);
	Source findOne(Long sourceId);
	void remove(Long sourceId);
	void toggle(Long sourceId);
	void save(Source source);
	void saveRule(Long sourceId,String script,RuleType ruleType);
	Map<Long, SourceStatistics> getSourceStatistics();
}
