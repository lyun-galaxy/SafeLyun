package com.paly.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.crypto.ExemptionMechanism;

import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paly.domain.Examswitch;
import com.paly.domain.Menu;
import com.paly.domain.Role;
import com.paly.domain.User;
import com.paly.mapper.ExamswitchMapper;
import com.paly.mapper.MenuMapper;
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
		// c		
		/*Role r1 = new Role("超级管理员", null);
		Role r2 = new Role("管理员", null);
		roleMapper.insert(r1);
		roleMapper.insert(r2);
		sqlSession.commit();*/
		
		// ru
		Role r = roleMapper.selectByPrimaryKey(1);
		String roleName = r.getRoleName();
		log.debug("roleName:" + roleName);
		r.setRoleName(roleName + "123");
		roleMapper.updateByPrimaryKey(r);
		log.debug("roleName after update:" + r.getRoleName());
	}
	
	// 设置用户拥有的角色
	@Test
	public void testSetUserAndRole() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		User u = userMapper.selectByPrimaryKey(2);
		Role r = roleMapper.selectByPrimaryKey(2);		
		userMapper.setUserHasRole(u.getUserId(), r.getRoleId());
		sqlSession.commit();
	}
	
	// 用户拥有角色
	@Test
	public void testQueryUserHasRoles() throws Exception {
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
	
	// 角色属于用户
	@Test
	public void testQueryRoleToUsers() throws Exception {
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
	
	@Test
	public void testMenu() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
		for (int i = 0; i < 3; i++) {
			Menu m = new Menu("菜单"+i, "http://" + i);
			menuMapper.insert(m);
		}
		sqlSession.commit();
	}
	
	// 设置角色拥有的菜单
	@Test
	public void testSetRoleAndMenu() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);		
		MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
		Role r = roleMapper.selectByPrimaryKey(2);
		Menu m = menuMapper.selectByPrimaryKey(3);
		roleMapper.setRoleHasMenu(r.getRoleId(), m.getMenuId());
		sqlSession.commit();
	}
	
	// 角色拥有菜单
	@Test
	public void testQueryRoleHasMenus() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);
		Role role = sqlSession.getMapper(RoleMapper.class).selectByPrimaryKey(1);
		List<Menu> menus = menuMapper.getMenusByRoleId(role.getRoleId());
		log.debug("menus:" + menus);
		for (Menu menu : menus) {
			log.debug("menu url:" + menu.getMenuUrl());
		}
	}
	
	// 菜单属于角色
	@Test
	public void testQueryMenuToRole() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		Menu menu = sqlSession.getMapper(MenuMapper.class).selectByPrimaryKey(1);
		List<Role> roles = roleMapper.getRolesByMenuId(menu.getMenuId());
		log.debug("roles:" + roles);
		for (Role role : roles) {
			log.debug("role name:" + role.getRoleName());
		}
		
	}
	
	@Test
	public void testExamswitch() {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		ExamswitchMapper examswitchMapper = sqlSession.getMapper(ExamswitchMapper.class);		
		// ru
		/*Examswitch examswitch = examswitchMapper.selectByPrimaryKey(1);
		log.debug("examswitch status:" + examswitch.getSwitchOnOrOff());
		examswitch.setSwitchOnOrOff(true);
		examswitchMapper.updateByPrimaryKey(examswitch);
		sqlSession.commit();
		log.debug("examswitch status after update:" + examswitch.getSwitchOnOrOff());*/
		
		// d
		/*try {
			int delId = examswitchMapper.deleteByPrimaryKey(1);
			log.debug("delete id:" + delId);			
		} catch (BindingException e) {
			log.error(e.getMessage());						
		}*/
		
		Examswitch examswitch = examswitchMapper.selectByPrimaryKey(2);
	}
}
