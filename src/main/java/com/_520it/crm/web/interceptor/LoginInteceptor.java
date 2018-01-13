package com._520it.crm.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com._520it.crm.domain.Employee;
import com._520it.crm.util.UserContext;

public class LoginInteceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//在必经之路上，将request放入ThreadLocal中
		UserContext.set(request);
		
		Employee user = (Employee)request.getSession().getAttribute(UserContext.user_in_session);
		if(user == null){
			response.sendRedirect("/login.jsp");
			return false;
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

	
	
	
}
