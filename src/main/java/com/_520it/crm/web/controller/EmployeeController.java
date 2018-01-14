package com._520it.crm.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.util.RequiredPermission;
import com._520it.crm.util.UserContext;

@Controller
public class EmployeeController {

	@Autowired
	IEmployeeService employeeService;

//见crmday2 video3
//相当于struts2中的prepareSaveOrUpdate方法
//	@ModelAttribute
//	public void before(Long id, Model model){
//		if(id != null){
//			//更新操作
//			Employee emp = employeeService.get(id);
//			model.addAttribute(emp);
//		}
//	}
	
	
	
	@RequiredPermission("员工登陆")
	@RequestMapping("/login")
	@ResponseBody
	public AjaxResult queryByLogin(String username, String password, HttpServletRequest request) {
		//在service方法前就需要先将request共享到threadlocal
		UserContext.set(request);
		AjaxResult result  = null;
		
		Employee employee = employeeService.queryByLogin(username, password);

		if (employee != null) {
			result = new AjaxResult(true, "登陆成功");
			request.getSession().setAttribute(UserContext.user_in_session, employee);
		} else {
			result = new AjaxResult( "账号或密码不正确！");
		}
		return result;
	}

	@RequiredPermission("员工页面")
	@RequestMapping("/employee")
	public String employee() {
		return "employee";
	}

	@RequiredPermission("员工列表")
	@RequestMapping("/employee_list")
	@ResponseBody
	public PageResult employeeList(EmployeeQueryObject qo) {
		PageResult pageResult = employeeService.selectByCondition(qo);
		return pageResult;
	}

	@RequiredPermission("员工保存")
	@RequestMapping("/employee_save")
	@ResponseBody
	public AjaxResult employeeSave(Employee e) {
		AjaxResult result  = null;

		e.setState(true);

		int count = employeeService.save(e);
		if (count == 1) {
			result = new AjaxResult(true, "保存成功！");
		} else {
			result = new AjaxResult( "保存失败！");
		}
		return result;
	}

	
	@RequiredPermission("员工更新")
	@RequestMapping("/employee_update")
	@ResponseBody
	public AjaxResult employeeUpdate(Employee e) {
		AjaxResult result  = null;
		System.out.println(e);
		int count = employeeService.update(e);
		if (count == 1) {
			result = new AjaxResult(true, "更新成功！");
		} else {
			result = new AjaxResult( "更新失败！");
		}
		return result;
	}

	
	@RequiredPermission("员工离职")
	@RequestMapping("/employee_changeState")
	@ResponseBody
	public AjaxResult employeeChangeState(Long id) {
		AjaxResult result  = null;

		int count = employeeService.changeState(id);
		if (count == 1) {
			result = new AjaxResult(true, "离职成功！");
		} else {
			result = new AjaxResult( "离职操作失败！");
		}
		return result;
	}

}
