package com.paly.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.paly.domain.User;
import com.paly.service.UserService;

@Controller
@RequestMapping("/user")
public class UserControler {
	@Resource
	private UserService userService;
	@RequestMapping(value="/findAll",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView findAll(HttpServletRequest request) throws Exception {
		List<User> list = userService.findAll();
		System.out.println(list);
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
		modelAndView.addObject("userList", list);

		// 指定视图
		// 下边的路径，如果在视图解析器中配置jsp路径的前缀和jsp路径的后缀，修改为
		// modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
		// 上边的路径配置可以不在程序中指定jsp路径的前缀和jsp路径的后缀
		modelAndView.setViewName("users/userList");

		return modelAndView;

	}
}
