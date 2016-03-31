package com.paly.test;

import java.security.MessageDigest;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paly.domain.Role;
import com.paly.domain.User;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.RoleMapper;
import com.paly.mapper.UserMapper;

public class DaoTest {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testUser() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// c
		/*MessageDigest md = MessageDigest.getInstance("MD5");
		for (int i = 0; i < 5; i++) {
			byte[] pass = md.digest((i+"pass").getBytes());
			User u = new User(i+"", pass.toString(), null);
			baseMapper.insert(u);
		}
		sqlSession.commit();
		*/
		
		// ru
		/*User u = userMapper.selectByPrimaryKey(2);
		log.debug("user name:" + u.getUserName());
		u.setUserName("张三");
		int upId = userMapper.updateByPrimaryKey(u);
		sqlSession.commit();
		log.debug("user name after update:" + u.getUserName() + "\t updateId:" + upId);*/
		
		// d
		int delId = userMapper.deleteByPrimaryKey(4);
		sqlSession.commit();
		log.debug("delete id:" + delId);
		
	}
	
	@Test
	public void testRole() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		Role r1 = new Role("超级管理员", null);
		Role r2 = new Role("管理员", null);
		roleMapper.insert(r1);
		roleMapper.insert(r2);
		sqlSession.commit();
	}
	
	@Test
	public void testUserAndRole() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		User u = userMapper.selectByPrimaryKey(2);
		Role r = roleMapper.selectByPrimaryKey(2);		
		userMapper.setUserHasRole(u.getUserId(), r.getRoleId());
		sqlSession.commit();
	}
	
	@Test
	public void testQueryUserHasRole() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		User u = userMapper.selectByPrimaryKey(2);
		List<Role> roles = roleMapper.getRolesByUserId(u.getUserId());
		log.debug("roles:" + roles);
		for (Role role : roles) {
			System.out.println(role.getRoleName());			
		}
	}
	
	@Test
	public void testQueryRoleHasUser() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		Role r = roleMapper.selectByPrimaryKey(1);
		List<User> users = userMapper.getUsersByRoleId(r.getRoleId());
		log.debug("users:" + users);
		for (User u : users) {
			System.out.println(u.getUserName());			
		}
	}
}
