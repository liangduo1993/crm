package com._520it.crm.service;

import java.util.List;

import com._520it.crm.domain.Role;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IRoleService {
	int save(Role role);
	int delete(Long id);
	int update(Role role);
	Role get(Long id);
	List<Role> selectAll();
	PageResult selectByCondition(QueryObject qo);
	PageResult queryRoleByEID(Long id);
}
