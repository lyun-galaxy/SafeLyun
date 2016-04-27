package com.paly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paly.domain.Menu;
import com.paly.domain.User;
import com.paly.service.MenuService;
import com.paly.util.MyJSONUtils;

/**
 * 后台首页Controller
 * 
 * @author root
 *
 */
@Controller
@RequestMapping("/manager_home")
public class AdminHomeController {
	@Resource
	private MenuService menuService;

	/**
	 * 跳转到后台首页
	 * 
	 * @return 后台首页URL
	 */
	@RequestMapping("/toHomePage")
	public String toHomePage() {
		return "admin/index.jsp";
	}

	/**
	 * 显示用户菜单
	 * 
	 * @param session
	 */
	@RequestMapping("/showMenuByUser")
	public void showMenuByUser(HttpServletResponse response, HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Menu> list = menuService.getMenuListTopByUser(user);
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<Map<String,Object>> children = new ArrayList<Map<String,Object>>();
		
		for(Menu menu : list){
			Map<String,Object> text = new HashMap<String,Object>();
			text.put("text", menu.getMenuName());
			Map<String,String> attributes = new HashMap<String,String>();
			attributes.put("url", menu.getMenuUrl());
			text.put("attributes", attributes);
			children.add(text);
		}
		map.put("text", "首页");
		map.put("children", children);
		maps.add(map);
		MyJSONUtils.writeJson(maps, response);
	}
}
