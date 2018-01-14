package com._520it.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com._520it.crm.domain.Role;
import com._520it.crm.query.QueryObject;

public interface RoleMapper {
	int insert(Role record);
    int deleteByPrimaryKey(Long id);
    int updateByPrimaryKey(Role record);
    Role selectByPrimaryKey(Long id);
    List<Role> selectAll();


	Long selectByConditionCount(QueryObject qo);
	List<Role> selectByCondition(QueryObject qo);
	int handleRelation(@Param("role_id")Long rold_id,@Param("permission_id")Long permission_id);
	int removeRelation(Long id);
	List<Role> queryRoleByEID(Long id);
}