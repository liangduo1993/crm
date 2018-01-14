package com._520it.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Permission;
import com._520it.crm.domain.Role;
import com._520it.crm.mapper.RoleMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	private RoleMapper dao;
		
	
	public int save(Role role) {
		int effectCount = dao.insert(role);
		List<Permission> permissions = role.getPermissions();
		for (Permission permission : permissions) {
			dao.handleRelation(role.getId(), permission.getId());
		}
		return effectCount;
	}

	public int delete(Long id) {
		return dao.deleteByPrimaryKey(id);
	}

	public int update(Role role) {
		dao.removeRelation(role.getId());
		int effectCount = dao.updateByPrimaryKey(role);
		List<Permission> permissions = role.getPermissions();
		for (Permission permission : permissions) {
			dao.handleRelation(role.getId(), permission.getId());
		}
		return effectCount;
	}

	public Role get(Long id) {
		return dao.selectByPrimaryKey(id);
	}

	public List<Role> selectAll() {
		return dao.selectAll();
	}

	public PageResult selectByCondition(QueryObject qo) {
		Long count = dao.selectByConditionCount(qo);
		if(count == 0l)
			return PageResult.EMPTY;
		else{
			List<Role> list = dao.selectByCondition(qo);
			return new PageResult(count, list);
		}
	}
	

	public PageResult queryRoleByEID(Long id){
		PageResult result = null;
		if(id > 0){
			 List<Role> list = dao.queryRoleByEID(id);
			 if(list.size() ==0){
				 result = PageResult.EMPTY;
			 }else
				 result = new PageResult(Long.valueOf(list.size()), list);
		}
		return result;
	}


}
