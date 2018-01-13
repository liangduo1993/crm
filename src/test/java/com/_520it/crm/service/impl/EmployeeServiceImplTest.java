package com._520it.crm.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com._520it.crm.domain.Employee;
import com._520it.crm.service.IEmployeeService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class EmployeeServiceImplTest {
	
	@Autowired
	IEmployeeService service;
	//EmployeeServiceImpl service; //当配置了aop pointcut，就只能注入接口
	
	

	@Test
	public void testSave() {
		Employee e = new Employee();
		service.save(e);
	}

	@Test
	public void testDelete() {
		service.delete(5l);
	}

	@Test
	public void testUpdate() {
	}

	@Test
	public void testGet() {
	}

	@Test
	public void testSelectAll() {
		List<Employee> selectAll = service.selectAll();
		System.out.println(selectAll);
	}

	@Test
	public void testSelectByCondition() {
	}

}
