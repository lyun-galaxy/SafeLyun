package com.paly.service;

import com.paly.domain.Role;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 角色Service接口
 * 
 * @author luohuaming
 */
public interface RoleService extends BaseService<Role> {

	/**
	 * 获取用户拥有的角色列表
	 * 
	 * @param userId
	 *            用户id
	 * @return 返回用户拥有的角色列表
	 */
	List<Role> getByUserId(int userId);

	/**
	 * 设置角色拥有哪个菜单
	 * 
	 * @param roleId
	 *            角色id
	 * @param menuId
	 *            将要拥有的菜单id
	 */
	void setRoleHasMenu(int roleId, int menuId);

	/**
	 * 获取菜单属于哪些角色
	 * 
	 * @param menuId
	 *            菜单id
	 * @return 返回菜单属于哪些角色的角色列表
	 */
	List<Role> getByMenuId(int menuId);
}