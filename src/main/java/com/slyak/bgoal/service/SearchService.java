/**
 * Project name : bgoal
 * File name : SearchService.java
 * Package name : com.slyak.bgoal.service
 * Date : 2013年12月9日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service;


import org.apache.lucene.search.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.slyak.bgoal.model.SearchResult;

public interface SearchService {

	Page<SearchResult<String, Object>> search(Pageable pageable, Query query);

}
