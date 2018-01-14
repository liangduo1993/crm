package com._520it.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.SystemLog;
import com._520it.crm.mapper.SystemLogMapper;
import com._520it.crm.service.ISystemLogService;

@Service
public class SystemLogServiceImpl implements ISystemLogService{
	@Autowired
	private SystemLogMapper dao;
	
	
	public void save(SystemLog log) {
		dao.insert(log);
	}

}
