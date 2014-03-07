/**
 * Project name : bgoal
 * File name : SourceServiceImpl.java
 * Package name : com.slyak.bgoal.service
 * Date : 2013年12月13日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.slyak.bgoal.dao.SourceDao;
import com.slyak.bgoal.dao.SourceRuleDao;
import com.slyak.bgoal.dao.SpecificDao;
import com.slyak.bgoal.enums.Status;
import com.slyak.bgoal.model.GroovyConfig;
import com.slyak.bgoal.model.JavaScriptConfig;
import com.slyak.bgoal.model.RuleType;
import com.slyak.bgoal.model.Source;
import com.slyak.bgoal.model.SourceRule;
import com.slyak.bgoal.model.SourceStatistics;

@Service
public class SourceServiceImpl implements SourceService,ApplicationContextAware {
	
	@Autowired
	private SourceDao sourceDao;
	
	@Autowired
	private SourceRuleDao sourceRuleDao;
	
	@Autowired
	private SpecificDao specificDao;

	private ApplicationContext applicationContext;
	

	/* (non-Javadoc)
	 * @see com.slyak.bgoal.service.SourceService#getSourceRule(java.lang.Long)
	 */
	@Override
	public SourceRule getSourceRule(Long sourceId) {
		return sourceRuleDao.findOne(sourceId);
	}

	@Override
	public List<Source> findAll() {
		return sourceDao.findAll();
	}

	@Override
	public Source findOne(Long sourceId) {
		return sourceDao.findOne(sourceId);
	}

	@Override
	public void remove(Long sourceId) {
		sourceDao.delete(sourceId);
	}

	@Override
	public List<Source> findByStatus(Status status) {
		return sourceDao.findByStatus(status);
	}

	@Override
	public void toggle(Long sourceId) {
		Source source = sourceDao.findOne(sourceId);
		source.setStatus(Status.values()[1-source.getStatus().ordinal()]);
		sourceDao.save(source);
	}

	@Override
	public void save(Source source) {
		sourceDao.save(source);
	}

	/* (non-Javadoc)
	 * @see com.slyak.bgoal.service.SourceService#saveRule(java.lang.Long, java.lang.String, com.slyak.bgoal.model.RuleType)
	 */
	@Override
	public void saveRule(Long sourceId, String script, RuleType ruleType) {
		SourceRule sourceRule = new SourceRule();
		sourceRule.setSourceId(sourceId);
		sourceRule.setType(ruleType);
		String rule = null;
		if(ruleType == RuleType.GROOVY){
			GroovyConfig gc = new GroovyConfig();
			gc.setContent(script);
			rule = JSON.toJSONString(gc);
		} else if (ruleType == RuleType.JAVASCRIPT){
			JavaScriptConfig jsc = new JavaScriptConfig();
			jsc.setDetailContentFunction(getFunctionString(script,"function\\s+getDetailContent"));
			jsc.setDetailTitleFunction(getFunctionString(script,"function\\s+getDetailTitle"));
			jsc.setDetailUrlsFunction(getFunctionString(script,"function\\s+getDetailUrls"));
			jsc.setNextPageUrlFunction(getFunctionString(script,"function\\s+getNextPageUrl"));
			rule = JSON.toJSONString(jsc);
		}
		sourceRule.setRule(rule);
		sourceRuleDao.save(sourceRule);
		
		for(HandlerContext hc : applicationContext.getBeansOfType(HandlerContext.class).values()){
			hc.clear(sourceId);
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
	private String getFunctionString(String script,String startPattern){
		int singleCount = 0;
		boolean findFirst = false;
		int end = 0;
		int begin = 0;
		Pattern pattern = Pattern.compile(startPattern);
		Matcher matcher = pattern.matcher(script);
		if(matcher.find()){
			begin = matcher.start();
		}
		for (int i = begin ;i < script.length();i++) {
			char c = script.charAt(i);
			if(c == '{'){
				if(findFirst){
					singleCount++;
				} else {
					findFirst = true;
				}
			}
			if(c == '}'){
				if(singleCount>0){
					singleCount= singleCount-1;
				} else {
					end = i;
					break;
				}
			}
		}
		return script.substring(begin,end+1);
	}
	
	
	public static void main(String[] args) {
		String aa = "  function  getNext";
		Pattern pattern = Pattern.compile("function\\s+getNext");
		Matcher matcher = pattern.matcher(aa);
		if(matcher.find()){
			System.out.println(matcher.start());
		}
	}

	/* (non-Javadoc)
	 * @see com.slyak.bgoal.service.SourceService#sourceArticleCount()
	 */
	@Override
	public Map<Long, SourceStatistics> getSourceStatistics() {
		return specificDao.getSourceStatistics();
	}
	

}
