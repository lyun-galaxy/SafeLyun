package com.paly.test;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.crypto.ExemptionMechanism;

import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.paly.domain.Classes;
import com.paly.domain.DatadicGroups;
import com.paly.domain.DatadicItems;
import com.paly.domain.Department;
import com.paly.domain.Examswitch;
import com.paly.domain.Itempool;
import com.paly.domain.Menu;
import com.paly.domain.Role;
import com.paly.domain.Score;
import com.paly.domain.Specialty;
import com.paly.domain.Student;
import com.paly.domain.User;
import com.paly.mapper.ClassesMapper;
import com.paly.mapper.DatadicGroupsMapper;
import com.paly.mapper.DepartmentMapper;
import com.paly.mapper.ExamswitchMapper;
import com.paly.mapper.ItempoolMapper;
import com.paly.mapper.MenuMapper;
import com.paly.mapper.RoleMapper;
import com.paly.mapper.ScoreMapper;
import com.paly.mapper.SpecialtyMapper;
import com.paly.mapper.StudentMapper;
import com.paly.mapper.UserMapper;

public class DaoTest {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testUser() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// c
		MessageDigest md = MessageDigest.getInstance("MD5");
		for (int i = 0; i < 5; i++) {
			byte[] pass = md.digest((i+"pass").getBytes());
			User u = new User("user"+i, pass.toString());
			userMapper.insert(u);
		}
		sqlSession.commit();
		
		
		// ru
		/*User u = userMapper.selectByPrimaryKey(2);
		log.debug("user name:" + u.getUserName());
		u.setUserName("张三");
		int upId = userMapper.updateByPrimaryKey(u);
		sqlSession.commit();
		log.debug("user name after update:" + u.getUserName() + "\t updateId:" + upId);*/
		
		// d
		/*int delId = userMapper.deleteByPrimaryKey(4);
		sqlSession.commit();
		log.debug("delete id:" + delId);*/
		
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
		List<Role> roles = roleMapper.getByUserId(u.getUserId());
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
		List<User> users = userMapper.getByRoleId(r.getRoleId());
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
		List<Menu> menus = menuMapper.getByRoleId(role.getRoleId());
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
		List<Role> roles = roleMapper.getByMenuId(menu.getMenuId());
		log.debug("roles:" + roles);
		for (Role role : roles) {
			log.debug("role name:" + role.getRoleName());
		}
		
	}
	
	@Test
	public void testExamswitch() {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		ExamswitchMapper examswitchMapper = sqlSession.getMapper(ExamswitchMapper.class);
		// c
		/*Examswitch examswitch = new Examswitch();
		examswitch.setSwitchOnOrOff(true);
		examswitchMapper.insert(examswitch);
		sqlSession.commit();
		*/
		// ru
		Examswitch examswitch = examswitchMapper.selectByPrimaryKey(1);
		log.debug("examswitch status:" + examswitch.getSwitchOnOrOff());
		examswitch.setSwitchOnOrOff(false); // 0
		examswitchMapper.updateByPrimaryKey(examswitch);
		sqlSession.commit();
		log.debug("examswitch status after update:" + examswitch.getSwitchOnOrOff());
		
		// d
		/*try {
			int delId = examswitchMapper.deleteByPrimaryKey(1);
			log.debug("delete id:" + delId);			
		} catch (BindingException e) {
			log.error(e.getMessage());						
		}*/
		
		// Examswitch examswitch = examswitchMapper.selectByPrimaryKey(2);
	}
		
	@Test
	public void testDepartment() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		DepartmentMapper dm = sqlSession.getMapper(DepartmentMapper.class);
		// c		
		
		/*Department department3 = new Department("化学与材料学院");		
		Department department4 = new Department("资源工程学院");		
		Department department5 = new Department("奇迈学院");		
		Department department6 = new Department("艺术与设计学院");		
		Department department7 = new Department("继续教育学院");		
		Department department8 = new Department("外国语学院");		
		Department department9 = new Department("教育科学学院");		
				
		List<Department> departments = new ArrayList<Department>();
		departments.add(department3);
		departments.add(department4);
		departments.add(department5);
		departments.add(department6);
		departments.add(department7);
		departments.add(department8);
		departments.add(department9);
		dm.batchAdd(departments);
		sqlSession.commit();*/
		
		// ru
		/*Department d = dm.selectByPrimaryKey(1);
		log.debug("department name:" + d.getDepartmentName());
		log.debug("department has specialties count:" + d.getSpecialties().size());
		d.setDepartmentName("数学与计算机科学学院");
		// d.setDepartmentId(3);	// 无法更新主键值
		dm.updateByPrimaryKey(d);
		sqlSession.commit();
		log.debug("department name after update:" + d.getDepartmentName());*/
				
		// d 删除主表
		/*int delId = dm.deleteByPrimaryKey(1);
		log.debug("delete id:" + delId);
		sqlSession.commit();*/
				
	}
	
	@Test
	public void testSpecialty() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		SpecialtyMapper sm = sqlSession.getMapper(SpecialtyMapper.class);
		DepartmentMapper dm = sqlSession.getMapper(DepartmentMapper.class);
			
		// c
		/*Department department1 = dm.selectByName("文学与传媒学院");
		Specialty s1 = new Specialty("汉语言文学教育专业");
		Specialty s2 = new Specialty("汉语言文学专业");
		Specialty s3 = new Specialty("汉语国际教育专业");
		s1.setDepartment(department1);
		s2.setDepartment(department1);		
		s3.setDepartment(department1);		
		sm.insert(s1);
		sm.insert(s2);
		sm.insert(s3);
		
		Department department2 = dm.selectByName("信息工程学院");
		Specialty s4 = new Specialty("数学与应用数学");
		Specialty s5 = new Specialty("计算机科学与技术");
		Specialty s6 = new Specialty("软件工程");
		s4.setDepartment(department2);		
		s5.setDepartment(department2);		
		s6.setDepartment(department2);		
		sm.insert(s4);
		sm.insert(s5);
		sm.insert(s6);*/
		
		// ru
		/*Specialty s = sm.selectByPrimaryKey(1);
		log.debug("specialty name:" + s.getSpecialtyName());
		log.debug("specialty belong to department:" + s.getDepartment().getDepartmentName());
		Department department = dm.selectByPrimaryKey(2);
		s.setDepartment(department);
		sm.updateByPrimaryKey(s);*/
		
		// d 删除从表
		/*int delId = sm.deleteByPrimaryKey(2);
		log.debug("delete id:" + delId);*/
		
		sqlSession.commit();
	}
	
	@Test
	public void testDepartmentAndSpecialty() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		SpecialtyMapper sm = sqlSession.getMapper(SpecialtyMapper.class);
		DepartmentMapper dm = sqlSession.getMapper(DepartmentMapper.class);
		// ru
		/*Specialty s = sm.selectByName("软件工程");		
		Department d = s.getDepartment();
		log.debug("department name:" + d.getDepartmentName());*/
		
		Department dept = dm.selectByName("信息工程学院");
		log.debug("department name:" + dept.getDepartmentName());
		sqlSession.commit();
	}
	
	@Test
	public void testClasses() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		ClassesMapper cm = sqlSession.getMapper(ClassesMapper.class);
		SpecialtyMapper sm = sqlSession.getMapper(SpecialtyMapper.class);
		Specialty specialty = sm.selectByName("软件工程");
		Specialty specialty1 = sm.selectByName("计算机科学与技术");
		Classes c1 = new Classes("软件工程1班", specialty);
		Classes c2 = new Classes("软件工程2班", specialty);
		Classes c3 = new Classes("计科1班", specialty1);
		Classes c4 = new Classes("计科2班", specialty1);
		cm.insert(c1);
		cm.insert(c2);		
		cm.insert(c3);		
		cm.insert(c4);		
		
		sqlSession.commit();
	}

	@Test
	public void testUserAndClasses() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		UserMapper um = sqlSession.getMapper(UserMapper.class);
		ClassesMapper cm = sqlSession.getMapper(ClassesMapper.class);
		// c
		/*Classes c1 = cm.selectByName("计科1班");
		Classes c2 = cm.selectByName("计科2班");
		User user = um.selectByPrimaryKey(1);
		um.setUserHasClasses(user.getUserId(), c1.getClassesId());
		um.setUserHasClasses(user.getUserId(), c2.getClassesId());*/
		
		// r
		
		
		sqlSession.commit();
	}
	
	@Test
	public void testStudent() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		StudentMapper sm = sqlSession.getMapper(StudentMapper.class);
		UserMapper um = sqlSession.getMapper(UserMapper.class);
		ClassesMapper cm = sqlSession.getMapper(ClassesMapper.class);
		
		// c		
		/*User user = um.selectByPrimaryKey(1);
		Classes classes = cm.selectByPrimaryKey(1);
		for (int i = 0; i < 5; i++) {
			Student s = new Student("201303450"+i, "student"+i+"号", "2013",
					"男", "123@gmail.com", user, classes);
			sm.insert(s);
		}
		
		User user1 = um.selectByPrimaryKey(2);
		Classes classes1 = cm.selectByPrimaryKey(2);
		for (int i = 0; i < 5; i++) {
			Student s = new Student("201403450"+i, "student"+i+"号", "2014",
					"男", "123@gmail.com", user1, classes1);
			sm.insert(s);
		}*/
		
	
		
		/*List<Object> list = sm.queryScoreByGrade("2013");
		List<Object> list1 = sm.queryFailScoreByGrade("2013");
		log.debug("list:" + list);
		log.debug("list1:" + list1);*/
		
		sqlSession.commit();
	}
	@Test
	public void testScore() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		ScoreMapper scoreMapper = sqlSession.getMapper(ScoreMapper.class);
		StudentMapper sm = sqlSession.getMapper(StudentMapper.class);
		Random rand = new Random(100);		
		List<Student> stus = sm.selectAll();	
		for (Student student : stus) {
			float mark = rand.nextFloat() * 100;	// 生成区间[0,100）的小数
			Score s = new Score(mark, 0, student);
			scoreMapper.insert(s);
		}
		
		sqlSession.commit();
	}
	
	@Test
	public void testDatadicGroup() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		DatadicGroupsMapper dgm = sqlSession.getMapper(DatadicGroupsMapper.class);
		List<DatadicItems> items = new ArrayList<DatadicItems>();
		for (int i = 0; i < 3; i++) {
			DatadicItems e = new DatadicItems();
			e.setItemCode("00"+i);
			e.setItemName("机电工程学院"+i);
			items.add(e);			
		}
		// 外键约束，DatadicGroups必须从数据库中取，而不能自己创建
		DatadicGroups itemsOfGroup = new DatadicGroups("001", "院系", items);	// 
		dgm.batchAddItemsOfGroup(itemsOfGroup);
		
		sqlSession.commit();
	}
	
	@Test
	public void testItemPool() throws Exception {
		SqlSession sqlSession = MyBatisDAOUtil.getSqlSessionFactory().openSession();
		ItempoolMapper im = sqlSession.getMapper(ItempoolMapper.class);
		List<Itempool> ip = im.queryByVagueQuestion("s");
		log.debug("testItemPool ip:" + ip.size());
	}
	
}
