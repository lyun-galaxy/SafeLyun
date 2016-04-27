package com.paly.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.paly.domain.Menu;
import com.paly.domain.Role;
import com.paly.domain.Student;
import com.paly.domain.User;
import com.paly.service.MenuService;
import com.paly.service.StudentService;

/**
 * 权限拦截器
 * 
 * @author root
 *
 */
public class PrivilegeInterceptor implements HandlerInterceptor {
	@Resource
	private StudentService studentService;
	@Resource
	private MenuService menuService;

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
			Student student = studentService.selectByStudentNumber(user.getUserName());
			if (student == null) {
				// 如果该用户不是学生
				if(url.matches(".*((manager_home)|(client_login)).*"))
					//可以访问后台首页界面
					return true;
				// 不能访问前台
				if (matchesClientURL(url)) {
					response.sendRedirect(request.getContextPath()+"/WEB-INF/jsp/public/noPrivilegeError.jsp");
					return false;
				}
				for (Role role : user.getRoles()) {
					List<Menu> menus = menuService.getByRoleId(role.getRoleId());
					for (Menu menu : menus) {
						String menuUrl = menu.getMenuUrl();
						if (!url.contains(menuUrl)) {
							response.sendRedirect(request.getContextPath()+"/WEB-INF/jsp/public/noPrivilegeError.jsp");
							return false;
						}
					}
				}
			} else {
				// 如果该用户是学生 不能访问后台
				if (matchesManagerUrl(url)) {
					response.sendRedirect(request.getContextPath()+"/WEB-INF/jsp/public/noPrivilegeError.jsp");
					return false;
				}
			}
		}
		// 如果还没登录
		return true;
	}

	/**
	 * 校验前台URL
	 * 
	 * @param url
	 * @return 前台中的所有URL是否包含当前URL
	 */
	private boolean matchesClientURL(String url) {
		return url.matches(".*((client_exam)|(client_home)|(client_score)|(client_study)).*");
	}

	/**
	 * 校验后台URL
	 * 
	 * @param url
	 * @return
	 */
	private boolean matchesManagerUrl(String url) {
		return url.matches(
				".*((reportPrint)|(chapter)|(manager_home)|(questionbank)|(sectionController)|(adminuser)|(switchController)).*");
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
