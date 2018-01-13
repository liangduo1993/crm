package com._520it.crm.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.SystemLog;

public class LogUtil {
	
	public void writeLog(JoinPoint joinPoint){
		SystemLog log = new SystemLog();
		HttpServletRequest request = UserContext.get();
		
		log.setOptime(new Date());
		if(request != null){
			log.setUser((Employee)request.getSession().getAttribute(UserContext.user_in_session));
			log.setOpip(request.getRemoteAddr());
		}
//		log.setFunction(function);
//		log.setParams(params);
		System.out.println("日志编写。。。。。。。");
	}
	
	
	
	
}
