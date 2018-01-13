package com._520it.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com._520it.crm.domain.Employee;
import com._520it.crm.query.QueryObject;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);


	Long selectByConditionCount(QueryObject qo);

	List<Employee> selectByCondition(QueryObject qo);

	Employee queryByLogin(@Param("username")String username,@Param("password")String password);

	int changeState(Long id);
}