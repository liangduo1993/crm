package com._520it.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IPermissionService;
import com._520it.crm.util.RequiredPermission;

@Controller
public class PermissionController {

	@Autowired
	private IPermissionService service;
	
	@RequiredPermission("权限列表")
	@RequestMapping("/permission_list")
	@ResponseBody
	public PageResult roleList(QueryObject qo) {
		return service.selectByCondition(qo);
	}

	@RequiredPermission("权限加载")
	@RequestMapping("/permission_load")
	@ResponseBody
	public AjaxResult load() {
		boolean success = service.load();
		if(success){
			return new AjaxResult(true, "加载权限成功！");
		}
		return new AjaxResult(false, "加载权限失败！");
	}

	
	
	@RequestMapping("/permission_queryByRID")
	@ResponseBody
	public PageResult queryByRID(Long id) {
		return service.queryByRID(id);
	}

}
