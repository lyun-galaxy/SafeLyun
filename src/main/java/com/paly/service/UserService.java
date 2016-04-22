package com.paly.service;

import java.util.List;

import com.paly.domain.User;

/**
 * 用户Service接口
 * @author luohuaming
 *
 */
public interface UserService extends BaseService<User> {
	/**
	 * 设置用户拥有哪个角色
	 * 
	 * @param userId
	 *            用户Id
	 * @param roleId
	 *            将要拥有的角色Id
	 */
	void setUserHasRole(int userId, int roleId);

	/**
	 * 获取角色下的所有用户列表
	 * 
	 * @param roleId
	 *            角色Id
	 * @return 返回角色下的所有用户列表
	 */
	List<User> getByRoleId(int roleId);

	/**
	 * 根据学生id获取与其关联的用户
	 * 
	 * @param studentId
	 *            学生id
	 * @return 返回与指定学生关联的用户
	 */
	User getByStudentId(int studentId);
	
	/**
	 * 通过用户名和密码获取用户
	 * @param username 用户名
	 * @param password 密码
	 * @return 用户
	 */
	User getByUsernameAndPassword(String username,String password);
}
