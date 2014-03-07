/**
 * Project name : bgoal
 * File name : SourceRuleDao.java
 * Package name : com.slyak.bgoal.dao
 * Date : 2013年12月19日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slyak.bgoal.model.SourceRule;

public interface SourceRuleDao extends JpaRepository<SourceRule, Long>{

}
