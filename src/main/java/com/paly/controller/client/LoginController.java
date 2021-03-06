package com.paly.controller.client;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paly.domain.Student;
import com.paly.domain.User;
import com.paly.service.RoleService;
import com.paly.service.StudentService;
import com.paly.service.UserService;

/**
 * 用户登录Controller
 * 
 * @author luohuaming
 *
 */
@Controller
@RequestMapping("/client_login")
public class LoginController {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private StudentService studentService;

	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(HttpSession session, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		User u = userService.getByUsernameAndPassword(user.getUserName(), user.getUserPassword());
		if (u == null) {
			map.put("check", false);
		} else {
			map.put("check", true);
			Student student = studentService.selectByStudentNumber(u.getUserName());
			if (student != null) {
				//如果当前用户是学生
				map.put("url", "client_login/toClientHomePage.action");
			} else {
				map.put("url", "client_login/toManagerHomePage.action");
			}
			session.setAttribute("user", u);
		}
		return map;
	}

	/**
	 * 重定向 到客户端主界面
	 * 
	 * @return
	 */
	@RequestMapping("toClientHomePage")
	public String toClientHomePage() {
		return "redirect:/client_home/toHomePage.action";
	}

	/**
	 * 重定向到后台管理界面
	 * 
	 * @return
	 */
	@RequestMapping("toManagerHomePage")
	public String toManagerHomePage() {
		return "redirect:/manager_home/toHomePage.action";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		return "redirect:/login.html";
	}
}
