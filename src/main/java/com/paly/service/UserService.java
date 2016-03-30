package com.paly.service;

import java.util.List;

import com.paly.domain.User;

public interface UserService {
	public List<User> findAll() throws Exception;
}
