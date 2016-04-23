package com.paly.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.paly.domain.User;

/**
 * 权限拦截器
 * 
 * @author root
 *
 */
public class PrivilegeInterceptor implements HandlerInterceptor {

	// 进入 Handler方法之前执行
	// 用于身份认证、身份授权
	// 比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 判断session
		HttpSession session = request.getSession();
		// 从session中取出用户身份信息
		User user = (User) session.getAttribute("user");
		if (user != null) {
			String url = request.getRequestURI();
			// 如果是管理员
			/*
			 * if(user.getType() == User.ADMINISTRATOR){ return true; }
			 * if(url.matches(".*((department)|(specialty)|(teacher)).*")){
			 * request.getRequestDispatcher(
			 * "/WEB-INF/jsp/public/noPrivilegeError.jsp").forward(request,
			 * response); return false; } if(user.getType() == User.STUDENT){
			 * if(url.matches(".*((add)|(edit)|(update)|(save)).*")){
			 * request.getRequestDispatcher(
			 * "/WEB-INF/jsp/public/noPrivilegeError.jsp").forward(request,
			 * response); return false; } }
			 */
		}
		// 如果还没登录
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
