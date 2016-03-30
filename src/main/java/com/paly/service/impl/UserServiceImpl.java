package com.paly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paly.domain.User;
import com.paly.mapper.UserMapper;
import com.paly.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	public List<User> findAll() throws Exception {
		List<User> list = userMapper.findUserByList();
		return list;
	}
}
