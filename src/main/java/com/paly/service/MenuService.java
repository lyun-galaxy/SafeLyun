package com.paly.service;

import java.util.List;
import java.util.Set;

import com.paly.domain.Menu;
import com.paly.domain.User;

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
	
	/**
	 * 获取用户的所有顶级菜单列表
	 * @param user
	 * @return 顶级菜单列表
	 */
	List<Menu> getMenuListTopByUser(User user);
	
	/**
	 * 获取管理员能访问的所有URL
	 * @param user 用户
	 * @return URL列表
	 */
	Set<String> getMenuListByUser(User user);
}