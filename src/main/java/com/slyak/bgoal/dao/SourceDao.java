/**
 * Project name : bgoal
 * File name : SourceDao.java
 * Package name : com.slyak.bgoal.dao
 * Date : 2013年11月27日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slyak.bgoal.enums.Status;
import com.slyak.bgoal.model.Source;

public interface SourceDao extends JpaRepository<Source, Long>{

	List<Source> findByStatus(Status status);

}
