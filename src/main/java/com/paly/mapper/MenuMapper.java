package com.paly.mapper;

import java.util.List;

import com.paly.domain.Menu;

public interface MenuMapper extends BaseMapper<Menu> {
   
	/**
	 * 根据MenuId获取其对应得父Menu
	 * @param menuId 菜单id
	 * @return 返回指定菜单id的父菜单
	 */
	Menu getParentMenus(int menuId);
	
	/**
	 *  查找出所有根菜单 
	 * @return 返回所有根菜单 
	 */
	List<Menu> getParentMenus();
	
	/**
	 * 获取某角色拥有的菜单列表
	 * @param roleId 角色id
	 * @return 返回查询某角色拥有的菜单列表
	 */
	List<Menu> getByRoleId(int roleId);
}