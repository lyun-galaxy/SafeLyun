package com.paly.controller.client;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paly.domain.User;
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

	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(HttpSession session, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		User u = userService.getByUsernameAndPassword(user.getUserName(), user.getUserPassword());
		if (u == null) {
			map.put("check", false);
		} else {
			map.put("check", true);
			map.put("url", "client_login/toHomePage.action");
			session.setAttribute("user", u);
		}
		return map;
	}

	/**
	 * 跳转到主界面
	 * 
	 * @return
	 */
	@RequestMapping("toHomePage")
	public String toHomePage() {
		return "redirect:/client_home/toHomePage.action";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		return "redirect:/login.html";
	}
}
