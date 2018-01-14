package com._520it.crm.service;

import java.util.List;

import com._520it.crm.domain.Permission;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IPermissionService {
	int save(Permission e);
	int delete(Long id);
	int update(Permission e);
	Permission get(Long id);
	List<Permission> selectAll();
	PageResult selectByCondition(QueryObject qo);
	boolean load();
	PageResult queryByRID(Long id);
}
