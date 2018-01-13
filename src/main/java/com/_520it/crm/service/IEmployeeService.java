package com._520it.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IEmployeeService {
	int save(Employee e);
	int delete(Long id);
	int update(Employee e);
	Employee get(Long id);
	List<Employee> selectAll();
	PageResult selectByCondition(QueryObject qo);
	Employee queryByLogin(String username, String password);
	int changeState(Long id);
}
