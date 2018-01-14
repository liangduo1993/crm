package com._520it.crm.mapper;

import java.util.List;

import com._520it.crm.domain.Department;

public interface DepartmentMapper {
    int insert(Department record);
    int updateByPrimaryKey(Department record);

    Department selectByPrimaryKey(Long id);
    List<Department> selectAll();

    
    
}