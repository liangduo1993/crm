package com._520it.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Employee;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService{
	
	@Autowired
	private EmployeeMapper dao;
		
	
	public int save(Employee e) {
		return dao.insert(e);
	}

	public int delete(Long id) {
		return dao.deleteByPrimaryKey(id);
	}

	public int update(Employee e) {
		return dao.updateByPrimaryKey(e);
	}

	public Employee get(Long id) {
		return dao.selectByPrimaryKey(id);
	}

	public List<Employee> selectAll() {
		return dao.selectAll();
	}

	public PageResult selectByCondition(QueryObject qo) {
		Long count = dao.selectByConditionCount(qo);
		if(count == 0l)
			return PageResult.EMPTY;
		else{
			List<Employee> list = dao.selectByCondition(qo);
			return new PageResult(count, list);
		}
	}
	
	public Employee queryByLogin(String username, String password){
		Employee e = dao.queryByLogin(username, password);
		return e;
	}

	@Override
	public int changeState(Long id) {
		return dao.changeState(id);
	}
	

}
