package com.paly.service;

import java.util.List;

import com.paly.domain.Menu;

/**
 * 菜单Service接口
 * @author luohuaming
 *
 */
public interface MenuService extends BaseService<Menu> {

	/**
	 * 获取某角色拥有的菜单列表
	 * 
	 * @param roleId
	 *            角色id
	 * @return 返回查询某角色拥有的菜单列表
	 */
	List<Menu> getByRoleId(int roleId);
}