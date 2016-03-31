package com.paly.mapper;

import java.util.List;

import com.paly.domain.Menu;

public interface MenuMapper extends BaseMapper<Menu> {
   
	/**
	 * 获取某角色拥有的菜单列表
	 * @param roleId 角色id
	 * @return 返回查询某角色拥有的菜单列表
	 */
	List<Menu> getMenusByRoleId(int roleId);
}