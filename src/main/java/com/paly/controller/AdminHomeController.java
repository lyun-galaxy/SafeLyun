package com.paly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paly.domain.Json;
import com.paly.domain.Menu;
import com.paly.domain.User;
import com.paly.service.MenuService;
import com.paly.service.UserService;
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
	@Resource
	private UserService userService;

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
	@RequestMapping("/modify_password")
	@ResponseBody
	public Json modifyPassword(String oldPassword,String newPassword,String reNewPassword,HttpSession session){
		Json json = new Json();

		User user = (User) session.getAttribute("user");
		// 判断从session获取的user是否为空
		if (user != null) {
			if (!oldPassword.trim().equals("") && !newPassword.equals("")) {
				if(!newPassword.equals(reNewPassword)){
					json.setSuccess(false);
					json.setMsg("确认密码和新密码不一致！");
					return json;
				}
				String oldPasswordMd5 =  DigestUtils.md5DigestAsHex(oldPassword.getBytes());
				// 判断原始密码是否正确
				if (oldPasswordMd5.equals(user.getUserPassword())) {
					String newPasswordMd5 = DigestUtils.md5DigestAsHex(newPassword.getBytes());
					user.setUserPassword(newPasswordMd5);
					// 修改密码
					userService.update(user);
					json.setSuccess(true);
					json.setMsg("成功修改密码！");
				} else {
					json.setMsg("原始密码错误！");
				}
			} else {
				json.setMsg("请不要输入空格！");
			}
		} else {
			json.setMsg("操作有误！");
		}
		return json;
	}
}
