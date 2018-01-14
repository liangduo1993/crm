package com._520it.crm.service;

import java.util.List;

import com._520it.crm.domain.Department;
import com._520it.crm.page.PageResult;

public interface IDepartmentService {
	int save(Department dept);
	int update(Department dept);
	Department get(Long id);
	List<Department> selectAll();
}
