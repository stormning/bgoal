/**
 * Project name : bgoal
 * File name : AdminController.java
 * Package name : com.slyak.bgoal.web
 * Date : 2013年12月13日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slyak.bgoal.model.Article;
import com.slyak.bgoal.model.GroovyConfig;
import com.slyak.bgoal.model.JavaScriptConfig;
import com.slyak.bgoal.model.RuleType;
import com.slyak.bgoal.model.SearchResult;
import com.slyak.bgoal.model.Source;
import com.slyak.bgoal.model.SourceRule;
import com.slyak.bgoal.service.ArticleService;
import com.slyak.bgoal.service.IndexProvider;
import com.slyak.bgoal.service.SearchService;
import com.slyak.bgoal.service.SourceService;
import com.slyak.bgoal.util.Constants;
import com.slyak.core.io.image.CommonImage;
import com.slyak.core.web.OffsetLimitRequest;
import com.slyak.user.model.User;
import com.slyak.user.service.UserService;
import com.slyak.user.service.impl.UserServiceImpl;
import com.slyak.user.util.UserQuery;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final String DEFAULT_PASSWORD = "123456";

	@Autowired
	private SourceService sourceService; 

	@Autowired
	private UserService userService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private SearchService searchService;

	@Autowired
	private IndexProvider<Article, Long> indexProvider;

	@Autowired
	private Analyzer analyzer;

	@RequestMapping({"/sources",""})
	public String sources(Model model){
		model.addAttribute("sources", sourceService.findAll());
		model.addAttribute("sourceArticleCount", sourceService.getSourceStatistics());

		return "admin.sources";
	}

	@RequestMapping("/source/{sourceId}/edit")
	public String sourceEdit(@PathVariable Long sourceId,Model model){
		model.addAttribute("source", sourceService.findOne(sourceId));
		SourceRule sourceRule = sourceService.getSourceRule(sourceId);
		if(sourceRule!=null){
			model.addAttribute("sourceRule",sourceRule);

			String ruleJson = sourceRule.getRule();
			if(sourceRule.getType()==RuleType.GROOVY){
				GroovyConfig config = JSON.parseObject(ruleJson, GroovyConfig.class);
				model.addAttribute("config", config);
			}else if (sourceRule.getType()==RuleType.JAVASCRIPT){
				JavaScriptConfig config = JSON.parseObject(ruleJson, JavaScriptConfig.class);
				model.addAttribute("config", config);
			}
		}
		return "admin.source";
	}

	@RequestMapping("/source/add")
	public String sourceAdd(Model model){
		return "admin.source";
	}

	@RequestMapping("/source/save")
	public String sourceSave(Source source,String script,String imgUrl,RuleType ruleType,Model model){
		//TODO ADD THEM TO SERVICE
		sourceService.save(source);
		if(!StringUtils.isEmpty(script)){
			sourceService.saveRule(source.getId(),script,ruleType);
		}
//		Resource r = resourceMappingManager.getRealPathByBizAndOwner(String.valueOf(Constants.TYPES.SOURCE), String.valueOf(source.getId()));
//		try {
//			String basePath = r.getFile().getPath();
//			File small = new File(basePath+File.separator+"small.jpg");
//			File big = new File(basePath+File.separator+"big.jpg");
//			CommonImage ci = new CommonImage(resourceMappingManager.getResourceByPath(imgUrl).getFile());
//			ci.resizeWithContainer(50, 50).save(small);
//			ci.resizeWithContainer(150, 150).save(big);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		return "redirect:/admin/sources";
	}

	@RequestMapping("/source/{sourceId}/toggle")
	public String sourceToggle(@PathVariable Long sourceId){
		sourceService.toggle(sourceId);
		return "redirect:/admin/sources";
	}


	@RequestMapping({"/articles"})
	public String articles(Pageable pageable,String key ,Long sourceId,Long creator,Model model) throws ParseException{
		BooleanQuery query = new BooleanQuery();
		query.add(NumericRangeQuery.newIntRange(Constants.FIELD_TYPE, Constants.TYPES.ARTICLE, Constants.TYPES.ARTICLE, true, true),Occur.MUST);
		if(sourceId!=null){
			model.addAttribute("source", sourceService.findOne(sourceId));
			query.add(NumericRangeQuery.newLongRange("sourceId", sourceId, sourceId, true, true),Occur.MUST);
		}
		if(creator!=null){
			model.addAttribute("creator", userService.getUser(creator));
			query.add(NumericRangeQuery.newLongRange("creator", creator, creator, true, true),Occur.MUST);
		}
		if(!StringUtils.isEmpty(key)){
			query.add(new QueryParser(Version.LUCENE_46,Constants.FIELD_SEARCH,analyzer).parse(key),Occur.MUST);
		}
		Page<SearchResult<String, Object>> page = searchService.search(pageable, query);
		model.addAttribute("sources",sourceService.findAll());
		model.addAttribute("page", page);
		return "admin.articles";
	}

	@RequestMapping("/article/{articleId}/edit")
	public String articleEdit(@PathVariable Long articleId,Model model){
		model.addAttribute("fake",getFake());
		Article article = articleService.findOne(articleId);
		model.addAttribute("article", articleService.findOne(articleId));
		Long creator = null;
		if((creator = article.getCreator())!=null) {
			model.addAttribute("creator", userService.getUser(creator));
		}
		return "admin.article";
	}

	@RequestMapping("/article/add")
	public String articleAdd(Model model){
		model.addAttribute("fake",getFake());
		return "admin.article";
	}
	
	private User getFake() {
		User fake = userService.findFakeUser();
		if(fake==null) {
			fake = userService.generateFakeUser(DEFAULT_PASSWORD);
		}
		return fake;
	}

	@RequestMapping("/article/save")
	public String articleSave(Article article,Model model){
		articleService.saveArticle(article);
		return "redirect:/admin/articles";
	}

	@RequestMapping("/article/{articleId}/toggle")
	public String articleToggle(@PathVariable Long articleId){
		articleService.toggleStatus(articleId);
		return "redirect:/admin/articles";
	}

	@RequestMapping({"/users"})
	public String users(Pageable pageable,String key ,Model model){
		UserQuery query = new UserQuery();
		query.setKeyword(key);
		Page<User> page = userService.pageUsers(pageable, query);
		if(page.getTotalElements()>0) {
			model.addAttribute("page", page);
			List<Long> userIds = new ArrayList<Long>();
			for (User user : page.getContent()) {
				userIds.add(user.getId());
			}
			model.addAttribute("usersArticleCount",articleService.getUsersArticleCount(userIds));
		}
		return "admin.users";
	}

	@RequestMapping({"/users/suggestion"})
	@ResponseBody
	public List<User> users(int limit,String keyword ,Model model){
		UserQuery query = new UserQuery();
		query.setKeyword(keyword);
		return userService.pageUsers(new OffsetLimitRequest(0, limit), query).getContent();
	}

	@RequestMapping("/user/{userId}/edit")
	public String userEdit(@PathVariable Long userId,Model model){
		model.addAttribute("user", userService.getUser(userId));
		return "admin.user";
	}

	@RequestMapping("/user/add")
	public String userAdd(Model model){
		return "admin.user";
	}

	@RequestMapping("/user/save")
	public String userSave(User user,Model model){
		if(user.getId() == null){
			userService.regist(user);
		} else {
			userService.update(user);
		}
		return "redirect:/admin/users";
	}

	@RequestMapping("/user/{userId}/toggle")
	public String userToggle(@PathVariable Long userId){
		userService.toggleStatus(userId);
		return "redirect:/admin/users";
	}

	@RequestMapping("/user/generate")
	public String userGenerate(){
		for (int i=0;i<10;i++) {
			User fake = userService.generateFakeUser(DEFAULT_PASSWORD);
		}
		return "redirect:/admin/users";
	}

	@RequestMapping("/index/rebuildAll")
	public String rebuildIndex(){
		indexProvider.rebuildAll();
		return "redirect:/admin/articles";
	}


}
