package com._520it.crm.service.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Permission;
import com._520it.crm.mapper.PermissionMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IPermissionService;
import com._520it.crm.util.PermissionUtil;
import com._520it.crm.util.RequiredPermission;

@Service
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private PermissionMapper dao;
	@Autowired
	private ApplicationContext ctx;

	public int save(Permission e) {
		return dao.insert(e);
	}

	public int delete(Long id) {
		return dao.deleteByPrimaryKey(id);
	}

	public int update(Permission e) {
		return dao.updateByPrimaryKey(e);
	}

	public Permission get(Long id) {
		return dao.selectByPrimaryKey(id);
	}

	public List<Permission> selectAll() {
		return dao.selectAll();
	}

	public PageResult selectByCondition(QueryObject qo) {
		Long count = dao.selectByConditionCount(qo);
		if (count == 0l)
			return PageResult.EMPTY;
		else {
			List<Permission> list = dao.selectByCondition(qo);
			return new PageResult(count, list);
		}
	}

	@Override
	public boolean load() {
		try {
			List<Permission> permissions = dao.selectAll();
			Set<String> expressions = new HashSet<>();
			for (Permission permission : permissions) {
				expressions.add(permission.getResource());
			}
			Map<String, Object> beansOfAnno = ctx.getBeansWithAnnotation(Controller.class);
			Collection<Object> controllers = beansOfAnno.values();
			for (Object controller : controllers) {
				Method[] methods = controller.getClass().getDeclaredMethods();
				for (Method method : methods) {
					RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
					if (annotation != null) {
						String name = annotation.value();
						String expression = PermissionUtil.buildExpression(method);
						if (!expressions.contains(expression)) {
							Permission permission = new Permission();
							permission.setName(name);
							permission.setResource(expression);
							dao.insert(permission);
						}
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public PageResult queryByRID(Long id) {
		PageResult result = null;
		if (id > 0) {
			List<Permission> list = dao.queryByRID(id);
			if(list.size() == 0)
				result = PageResult.EMPTY;
			else
				result = new PageResult(Long.valueOf(list.size()), list);
		}
		return result;
	}

}
