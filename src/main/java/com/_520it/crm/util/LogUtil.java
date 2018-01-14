package com._520it.crm.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.SystemLog;
import com._520it.crm.service.ISystemLogService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LogUtil {
	@Autowired
	private ISystemLogService service;
	
	
	//JoinPoint是spring在aop切面的时候，自动传入的参数，封装了之前执行的service的信息
	public void writeLog(JoinPoint joinPoint) throws JsonProcessingException {
		if(joinPoint.getTarget() instanceof ISystemLogService){
			return;
		}
		
		
		SystemLog log = new SystemLog();
		HttpServletRequest request = UserContext.get();

		log.setOptime(new Date());
		log.setUser((Employee) request.getSession().getAttribute(UserContext.user_in_session));
		log.setOpip(request.getRemoteAddr());

		String className = joinPoint.getTarget().getClass().getName();// 类权限定名
		String functionName = joinPoint.getSignature().getName();// 方法名
		String function = className + ":" + functionName;// 权限表达式
		log.setFunction(function);
		
		//jackson包自带的tojson方法
		ObjectMapper om = new ObjectMapper();
		//如果参数为null，就不转换为json
		om.setSerializationInclusion(Include.NON_NULL);
		String params = om.writeValueAsString(joinPoint.getArgs());
		log.setParams(params);
		service.save(log);
		System.out.println("日志编写。。。。。。。");
	}

}
