package com.paly.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Menu;
import com.paly.domain.Role;
import com.paly.domain.Student;
import com.paly.domain.User;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.MenuMapper;
import com.paly.service.MenuService;
import com.paly.service.StudentService;

/**
 * 菜单Service接口实现
 * 
 * @author root
 *
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {
	@Resource
	private MenuMapper menuMapper;
	@Resource
	private StudentService studentService;

	@Override
	public List<Menu> getByRoleId(int roleId) {
		return menuMapper.getByRoleId(roleId);
	}

	@Override
	public BaseMapper<Menu> getBaseMapper() {
		return menuMapper;
	}

	@Override
	public List<Menu> getTop() {
		return menuMapper.getParentMenus();
	}

	@Override
	public List<String> getMenuUrls() {
		List<Menu> list = menuMapper.selectAll();
		List<String> urls = new ArrayList<String>();
		for (Menu menu : list) {
			urls.add(menu.getMenuUrl());
		}
		return urls;
	}

	@Override
	public List<Menu> getMenuListTopByUser(User user) {
		List<Menu> list = new ArrayList<Menu>();
		Student student = studentService.selectByStudentNumber(user.getUserName());
		if (student != null) {
			// 当前用户为学生
			return list;
		}
		for (Role role : user.getRoles()) {
			List<Menu> menus = getByRoleId(role.getRoleId());
			for (Menu menu : menus) {
				System.out.println(menu.getParentMenu()+"-----");
				if (menu.getParentMenu() == null) {
					// 添加顶级目录
					list.add(menu);
				}
			}
		}
		return list;
	}

	@Override
	public Set<String> getMenuListByUser(User user) {
		Set<String> list = new HashSet<String>();
		Student student = studentService.selectByStudentNumber(user.getUserName());
		if (student != null) {
			// 当前用户为学生
			return list;
		}
		for (Role role : user.getRoles()) {
			List<Menu> menus = getByRoleId(role.getRoleId());
			for (Menu menu : menus) {
				list.add(menu.getMenuUrl());
			}
		}
		return list;
	}
}
