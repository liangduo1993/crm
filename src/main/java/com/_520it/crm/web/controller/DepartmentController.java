package com._520it.crm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com._520it.crm.domain.Department;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.service.IDepartmentService;
import com._520it.crm.util.RequiredPermission;

@Controller
public class DepartmentController {

	@Autowired
	IDepartmentService departmentService;

	
	

	@RequiredPermission("部门页面")
	@RequestMapping("/department")
	public String Department() {
		return "Department";
	}

	@RequiredPermission("部门列表")
	@RequestMapping("/department_list")
	@ResponseBody
	public List<Department> DepartmentList() {
		return departmentService.selectAll();
	}

	@RequiredPermission("部门保存")
	@RequestMapping("/department_save")
	@ResponseBody
	public AjaxResult DepartmentSave(Department e) {
		AjaxResult result  = null;

		e.setState(true);

		int count = departmentService.save(e);
		if (count == 1) {
			result = new AjaxResult(true, "保存成功！");
		} else {
			result = new AjaxResult( "保存失败！");
		}
		return result;
	}

	
	@RequiredPermission("部门更新")
	@RequestMapping("/department_update")
	@ResponseBody
	public AjaxResult DepartmentUpdate(Department e) {
		AjaxResult result  = null;
		System.out.println(e);
		int count = departmentService.update(e);
		if (count == 1) {
			result = new AjaxResult(true, "更新成功！");
		} else {
			result = new AjaxResult( "更新失败！");
		}
		return result;
	}

	

}
