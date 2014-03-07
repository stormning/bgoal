/**
 * Project name : bgoal
 * File name : HomeController.java
 * Package name : com.slyak.bgoal.web
 * Date : 2013-11-27
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.web;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.slyak.bgoal.enums.ArticleStatus;
import com.slyak.bgoal.model.Article;
import com.slyak.bgoal.model.SearchResult;
import com.slyak.bgoal.service.ArticleService;
import com.slyak.bgoal.service.SearchService;
import com.slyak.bgoal.service.SourceService;
import com.slyak.bgoal.util.Constants;
import com.slyak.user.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private SourceService sourceService;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Analyzer analyzer;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		//recent regist
		model.addAttribute("recentRegist", userService.recentRegist(5));
		
		return "home.index";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@PageableDefault(size=10) Pageable pageable,String key, Model model) throws ParseException {
		BooleanQuery query = new BooleanQuery();
		query.add(NumericRangeQuery.newIntRange(Constants.FIELD_TYPE, Constants.TYPES.ARTICLE, Constants.TYPES.ARTICLE, true, true),Occur.MUST);
		if(StringUtils.hasText(key)){
			query.add(new QueryParser(Version.LUCENE_46,Constants.FIELD_SEARCH,analyzer).parse(QueryParser.escape(key)),Occur.MUST);
		}
		query.add(new TermQuery(new Term("status",ArticleStatus.NORMAL.name())), Occur.MUST);
		Page<SearchResult<String, Object>> page =  searchService.search(pageable, query);
		model.addAttribute("timelines", articleService.renderTimelines(page.getContent()));
		model.addAttribute("page", page);
		return "home.search";
	}
	
	@RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
	public String article(@PathVariable Long articleId,Model model) {
		Article article = articleService.findOne(articleId);
		model.addAttribute("article",article);
		if(article.getSourceId() == null){
			model.addAttribute("creator",userService.getUser(article.getCreator()));
		} else {
			model.addAttribute("source",sourceService.findOne(article.getSourceId()));	
		}
		return "home.article";
	}
	
	public static void main(String[] args) {
//		File f = new File("/opt/bgoal/searcher");
//		System.out.println(f.exists());
//		try {
//			SimpleFSDirectory dd = new SimpleFSDirectory(f);
//			dd.fileExists("aa.txt");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.print(ArticleStatus.NORMAL.name());
	}
	
}
