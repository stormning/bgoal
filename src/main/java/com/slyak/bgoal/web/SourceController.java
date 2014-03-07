/**
 * Project name : bgoal
 * File name : SourceController.java
 * Package name : com.slyak.bgoal.web
 * Date : 2014年1月14日
 * Copyright : 2014 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.slyak.bgoal.service.SourceService;

@Controller
@RequestMapping("/source")
public class SourceController {
	
	@Autowired
	private SourceService sourceService;

	@RequestMapping("/{sourceId}")
	public String index(@PathVariable Long sourceId,Model model){
		model.addAttribute("source", sourceService.findOne(sourceId));
		return "source.index";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable,String key, Model model) {
		model.addAttribute("sources",sourceService.findAll());
		model.addAttribute("keywords","");
		return "source.list";
	}
}
