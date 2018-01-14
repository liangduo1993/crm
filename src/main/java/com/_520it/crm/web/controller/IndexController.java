package com._520it.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com._520it.crm.util.RequiredPermission;

@Controller
public class IndexController {

	
	@RequiredPermission("主页面访问")
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
}
