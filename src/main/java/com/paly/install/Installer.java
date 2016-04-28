package com.paly.install;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.paly.domain.Classes;
import com.paly.domain.Menu;
import com.paly.domain.Role;
import com.paly.domain.User;
import com.paly.service.ClassesService;
import com.paly.service.MenuService;
import com.paly.service.RoleService;
import com.paly.service.UserService;
import com.paly.util.Common;

/**
 * 初始化权限信息
 * 
 * @author luohuaming
 *
 */
@Component
public class Installer {

	@Resource
	private RoleService roleService;
	@Resource
	private MenuService menuService;
	@Resource
	private UserService userService;
	@Resource
	private ClassesService classesService;

	public void install() {
		Menu menu, menu1, menu2, menu3, menu4;
		menu = new Menu();
		menu.setMenuId(1);
		menu.setMenuName("学生信息");
		menu.setMenuUrl("/admin/xsgl/xsgl.jsp");

		menu1 = new Menu();
		menu1.setMenuId(2);
		menu1.setMenuName("章节管理");
		menu1.setMenuUrl("/admin/zjgl/zjgl.jsp");

		menu2 = new Menu();
		menu2.setMenuId(3);
		menu2.setMenuName("题库管理");
		menu2.setMenuUrl("/admin/tkgl/tkgl.jsp");

		menu3 = new Menu();
		menu3.setMenuId(4);
		menu3.setMenuName("报表查看");
		menu3.setMenuUrl("/admin/bbgl/bbgl.jsp");

		menu4 = new Menu();
		menu4.setMenuId(5);
		menu4.setMenuName("应用监控");
		menu4.setMenuUrl("/admin/yyjk/yyjk.jsp");

		menuService.save(menu);
		menuService.save(menu1);
		menuService.save(menu2);
		menuService.save(menu3);
		menuService.save(menu4);

		// 章节管理
		Menu chapter1 = new Menu("chapterManager", "/chapter", menu1);
		chapter1.setMenuId(6);
		Menu chapter2 = new Menu("sectionController", "/sectionController", menu1);
		chapter2.setMenuId(7);
		menuService.save(chapter1);
		menuService.save(chapter2);

		// 题库管理
		Menu questionbank = new Menu("questionbankManager", "/questionbank", menu2);
		questionbank.setMenuId(8);
		menuService.save(questionbank);

		// 报表查看
		Menu report = new Menu("reportManager", "/report", menu3);
		report.setMenuId(9);
		menuService.save(report);
		Menu report2 = new Menu("reportPrintManager", "/reportPrint", menu3);
		report2.setMenuId(10);
		menuService.save(report2);

		// 学生信息
		Menu adminuser = new Menu("adminuserManager", "/adminuser", menu);
		adminuser.setMenuId(11);
		Menu switchController = new Menu("switchController", "/switchController", menu);
		switchController.setMenuId(12);
		menuService.save(adminuser);
		menuService.save(switchController);

		// 应用监控
		Menu applicationMonitoring = new Menu("applicationMonitoring", "/druid", menu4);
		applicationMonitoring.setMenuId(13);
		menuService.save(applicationMonitoring);

		Role role = new Role();
		role.setRoleName(Common.ROLE_NAME_ADMINISTRATORS);
		role.setRoleId(1);

		Role role2 = new Role();
		role2.setRoleId(2);
		role2.setRoleName(Common.ROLE_NAME_INSTRUCTOR);

		Role role3 = new Role();
		role3.setRoleName("运维管理员");
		role3.setRoleId(3);

		roleService.save(role);
		roleService.save(role2);
		roleService.save(role3);

		// 设置普通管理员的角色
		roleService.setRoleHasMenu(role.getRoleId(), menu.getMenuId());
		roleService.setRoleHasMenu(role.getRoleId(), menu1.getMenuId());
		roleService.setRoleHasMenu(role.getRoleId(), menu2.getMenuId());
		roleService.setRoleHasMenu(role.getRoleId(), menu3.getMenuId());
		roleService.setRoleHasMenu(role.getRoleId(), chapter1.getMenuId());
		roleService.setRoleHasMenu(role.getRoleId(), chapter2.getMenuId());
		roleService.setRoleHasMenu(role.getRoleId(), adminuser.getMenuId());
		roleService.setRoleHasMenu(role.getRoleId(), switchController.getMenuId());
		roleService.setRoleHasMenu(role.getRoleId(), report.getMenuId());
		roleService.setRoleHasMenu(role.getRoleId(), report2.getMenuId());

		// 设置运维管理员
		roleService.setRoleHasMenu(role3.getRoleId(), menu.getMenuId());
		roleService.setRoleHasMenu(role3.getRoleId(), menu1.getMenuId());
		roleService.setRoleHasMenu(role3.getRoleId(), menu2.getMenuId());
		roleService.setRoleHasMenu(role3.getRoleId(), menu3.getMenuId());
		roleService.setRoleHasMenu(role3.getRoleId(), menu4.getMenuId());
		roleService.setRoleHasMenu(role3.getRoleId(), chapter1.getMenuId());
		roleService.setRoleHasMenu(role3.getRoleId(), chapter2.getMenuId());
		roleService.setRoleHasMenu(role3.getRoleId(), adminuser.getMenuId());
		roleService.setRoleHasMenu(role3.getRoleId(), switchController.getMenuId());
		roleService.setRoleHasMenu(role3.getRoleId(), report.getMenuId());
		roleService.setRoleHasMenu(role3.getRoleId(), report2.getMenuId());
		roleService.setRoleHasMenu(role3.getRoleId(), applicationMonitoring.getMenuId());

		roleService.setRoleHasMenu(role2.getRoleId(), menu3.getMenuId());
		roleService.setRoleHasMenu(role2.getRoleId(), report.getMenuId());
		roleService.setRoleHasMenu(role2.getRoleId(), report2.getMenuId());

		// 运维管理员
		User admin = new User();
		admin.setUserName("2013034589");
		admin.setUserPassword("123456");
		// 普通管理员
		User admin1 = new User();
		admin1.setUserName("2013034590");
		admin1.setUserPassword("123456");

		List<Classes> classes = classesService.findAll();
		// 辅导员
		User admin3 = new User();
		admin3.setUserName("2013034591");
		admin3.setUserPassword("123456");

		userService.save(admin);
		admin = userService.getByUsernameAndPassword(admin.getUserName(), "123456");
		userService.save(admin1);
		admin1 = userService.getByUsernameAndPassword(admin1.getUserName(), "123456");
		userService.save(admin3);
		admin3 = userService.getByUsernameAndPassword(admin3.getUserName(), "123456");

		userService.setUserHasRole(admin.getUserId(), role3.getRoleId());
		userService.setUserHasRole(admin1.getUserId(), role.getRoleId());
		userService.setUserHasRole(admin3.getUserId(), role2.getRoleId());

		for (Classes c : classes) {
			userService.setUserHasClasses(admin3.getUserId(), c.getClassesId());
		}
	}

	public static void main(String[] args) {
		System.out.println("正在执行安装...");
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();
		System.out.println("== 安装完毕 ==");
	}
}
