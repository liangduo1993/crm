package com._520it.crm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com._520it.crm.domain.Role;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IRoleService;
import com._520it.crm.util.RequiredPermission;

@Controller
public class RoleController {

	@Autowired
	private IRoleService roleService;
	


	@RequiredPermission("角色页面")
	@RequestMapping("/role")
	public String role() {
		return "role";
	}

	@RequestMapping("/role_list")
	@ResponseBody
	public PageResult roleList(QueryObject qo) {
		PageResult pageResult = roleService.selectByCondition(qo);
		return pageResult;
	}
	
	@RequestMapping("/role_queryRoleByEID")
	@ResponseBody
	public PageResult queryRoleByEID(Long id) {
		PageResult pageResult = roleService.queryRoleByEID(id);
		return pageResult;
	}

	@RequiredPermission("角色保存")
	@RequestMapping("/role_save")
	@ResponseBody
	public AjaxResult roleSave(Role role) {
		AjaxResult result  = null;
		
		System.out.println(role);
		
		int count = roleService.save(role);
		if (count == 1) {
			result = new AjaxResult(true, "保存成功！");
		} else {
			result = new AjaxResult( "保存失败！");
		}
		return result;
	}

	@RequiredPermission("角色更新")
	@RequestMapping("/role_update")
	@ResponseBody
	public AjaxResult roleUpdate(Role role) {
		AjaxResult result  = null;

		int count = roleService.update(role);
		if (count == 1) {
			result = new AjaxResult(true, "更新成功！");
		} else {
			result = new AjaxResult( "更新失败！");
		}
		return result;
	}


}
