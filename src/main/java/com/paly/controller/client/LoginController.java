package com.paly.controller.client;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping("login")
	public ModelAndView login(HttpSession session, User user) {
		ModelAndView modelAndView = new ModelAndView();
		// User u = userService.findByNumberAndNameAndPassword(user);
		User u = userService.getByUsernameAndPassword(user.getUserName(), user.getUserPassword());
		if (u == null) {
			modelAndView.addObject("usernameError", "学号/教工号、姓名或密码错误");
			modelAndView.addObject("user", user);
			modelAndView.setViewName("../../login.html");
		} else {
			session.setAttribute("user", u);
			modelAndView.setViewName("redirect:/client_home/toHomePage.action");
		}
		return modelAndView;
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "../../login.html";
	}
}
