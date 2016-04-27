package com.paly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台首页Controller
 * @author root
 *
 */
@Controller
@RequestMapping("/manager_home")
public class AdminHomeController {
	@RequestMapping("/toHomePage")
	public String toHomePage(){
		return "admin/index.jsp";
	}
}
