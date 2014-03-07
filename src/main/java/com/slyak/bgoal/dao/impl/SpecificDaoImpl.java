/**
 * Project name : bgoal
 * File name : SpecificDaoImpl.java
 * Package name : com.slyak.bgoal.dao.impl
 * Date : 2013年12月24日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.slyak.bgoal.dao.SpecificDao;
import com.slyak.bgoal.model.SourceStatistics;

@Repository
@SuppressWarnings("unchecked")
public class SpecificDaoImpl implements SpecificDao {
	
	@PersistenceContext
	private EntityManager em;

	/* (non-Javadoc)
	 * @see com.slyak.bgoal.dao.SpecificDao#sourceCountMap()
	 */
	@Override
	public Map<Long, SourceStatistics> getSourceStatistics() {
		Query nq = em.createNativeQuery("SELECT ta.source_id as sid, count(*) AS acount, max(ta.create_at) AS ctime  FROM t_article ta where ta.source_id>0 GROUP BY ta.source_id");
		List<Object[]> result = nq.getResultList();
		if(CollectionUtils.isEmpty(result)){
			return Collections.emptyMap();
		}else{
			Map<Long,SourceStatistics> sourceStatisticsMap = new HashMap<Long, SourceStatistics>();
			for (Object[] objs : result) {
				SourceStatistics ss = new SourceStatistics();
				ss.setCount(((Number)objs[1]).longValue());
				ss.setLastUpdate((Date)objs[2]);
				sourceStatisticsMap.put(((Number)objs[0]).longValue(), ss);
			}
			return sourceStatisticsMap;
		}
	}

	/* (non-Javadoc)
	 * @see com.slyak.bgoal.dao.SpecificDao#getUsersArticleCount(java.util.List)
	 */
	@Override
	public Map<Long, Long> getUsersArticleCount(List<Long> userIds) {
		Query nq = em.createNativeQuery("SELECT ta.creator,count(ta.creator) as acount FROM t_article ta WHERE ta.creator in (:userIds) GROUP BY ta.creator");
		nq.setParameter("userIds", userIds);
		List<Object[]> result = nq.getResultList();
		if(CollectionUtils.isEmpty(result)){
			return Collections.emptyMap();
		}else{
			Map<Long,Long> usersArticleCount = new HashMap<Long, Long>();
			for (Object[] objs : result) {
				usersArticleCount.put(((Number)objs[0]).longValue(), ((Number)objs[1]).longValue());
			}
			return usersArticleCount;
		}
	}

}
