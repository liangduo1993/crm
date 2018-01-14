package com._520it.crm.mapper;

import java.util.List;

import com._520it.crm.domain.Permission;
import com._520it.crm.query.QueryObject;

public interface PermissionMapper {
	int insert(Permission record);
    int deleteByPrimaryKey(Long id);
    int updateByPrimaryKey(Permission record);
    Permission selectByPrimaryKey(Long id);
    List<Permission> selectAll();
    List<Permission> selectByCondition(QueryObject qo);
    Long selectByConditionCount(QueryObject qo);
    
    List<Permission> queryByRID(Long role_id);
}