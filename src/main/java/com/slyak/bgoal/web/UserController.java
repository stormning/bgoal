/**
 * Project name : bgoal
 * File name : UserController.java
 * Package name : com.slyak.bgoal.web
 * Date : 2013年12月27日
 * Copyright : 2013 , SLYAK.COM All Rights Reserved
 * Author : stormning@163.com
 */
package com.slyak.bgoal.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "alone:user.login";
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String regist(Model model) {
		return "alone:user.regist";
	}
	
	@RequestMapping(value = "/fogot", method = RequestMethod.GET)
	public String fogot(Model model) {
		return "alone:user.fogot";
	}

	@RequestMapping("/profile")
	public String profile(){
		return "user.profile";
	}
	
	@RequestMapping("/{userId}/profile")
	public String user(@PathVariable Long userId){
		return "user.profile";
	}
}
