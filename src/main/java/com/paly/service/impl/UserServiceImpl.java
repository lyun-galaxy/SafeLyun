package com.paly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
	public void save(User entity) {
		// 密码md5加密
		String password = entity.getUserPassword();
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		entity.setUserPassword(password);
		super.save(entity);
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

	@Override
	public User getByUsernameAndPassword(String username, String password) {
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		return userMapper.queryByNameAndPasswd(username, password);
	}

	@Override
	public void setUserHasClasses(int userId, int classesId) {
		userMapper.setUserHasClasses(userId, classesId);
	}
}
