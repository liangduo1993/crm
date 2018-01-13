package com._520it.crm.util;

import javax.servlet.http.HttpServletRequest;

public class UserContext {

	public static final String user_in_session = "USER_IN_SESSION";
	
	private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<>();
	
	//将request对象绑定到ThreadLocal线程中
	public static void set(HttpServletRequest request){
		threadLocal.set(request);
	}
	
	public static HttpServletRequest get(){
		return threadLocal.get();
	}
	
}
