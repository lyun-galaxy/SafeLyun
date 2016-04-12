package com.paly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.User;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.UserMapper;
import com.paly.service.UserService;

/**
 * 用户Service实现  
 * 
 * @author luohuaming
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	public BaseMapper<User> getBaseMapper() {
		return userMapper;
	}

	@Override
	public void setUserHasRole(int userId, int roleId) {
		userMapper.setUserHasRole(userId, roleId);
	}

	@Override
	public List<User> getByRoleId(int roleId) {
		return userMapper.getByRoleId(roleId);
	}

	@Override
	public User getByStudentId(int studentId) {
		return userMapper.getByStudentId(studentId);
	}
}
