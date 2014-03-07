/**
 * Project name : bgoal
 * File name : ArticleDao.java
 * Package name : com.slyak.bgoal.dao
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slyak.bgoal.model.Article;

public interface ArticleDao extends JpaRepository<Article, Long>{

	List<Article> findByOldUrlShortIn(Set<String> urls);

	Article findByOldUrlShort(String oldUrlShort);

}
