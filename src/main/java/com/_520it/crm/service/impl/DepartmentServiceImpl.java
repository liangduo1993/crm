package com._520it.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Department;
import com._520it.crm.mapper.DepartmentMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService{
	
	@Autowired
	private DepartmentMapper dao;
		
	
	public int save(Department e) {
		int effectCount = dao.insert(e);
		return effectCount;
	}


	public int update(Department e) {
		int effectCount = dao.updateByPrimaryKey(e);
		return effectCount;
	}

	public Department get(Long id) {
		return dao.selectByPrimaryKey(id);
	}

	public List<Department> selectAll() {
//		PageResult result = null;
//		
//		List<Department> list = dao.selectAll();
//		if(list.size() ==0){
//			result = PageResult.EMPTY;
//		}else{
//			result = new PageResult(Long.valueOf(list.size()), list);
//		}
		return dao.selectAll();
	}



	

}
