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

	/**
	 * 获取所有顶级菜单
	 * @return 所有顶级菜单列表
	 */
	List<Menu> getTop();

	/**
	 * 获取所有菜单的URL
	 * @return  所有菜单的URL
	 */
	List<String> getMenuUrls();
}