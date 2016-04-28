package com.paly.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;

import com.paly.domain.Classes;
import com.paly.domain.Department;
import com.paly.domain.Examswitch;
import com.paly.domain.Itempool;
import com.paly.domain.Menu;
import com.paly.domain.Role;
import com.paly.domain.Score;
import com.paly.domain.Section;
import com.paly.domain.Specialty;
import com.paly.domain.Student;
import com.paly.domain.Subsection;
import com.paly.domain.User;
import com.paly.service.ClassesService;
import com.paly.service.DepartmentService;
import com.paly.service.ExamswitchService;
import com.paly.service.ItempoolService;
import com.paly.service.MenuService;
import com.paly.service.RoleService;
import com.paly.service.ScoreService;
import com.paly.service.SectionService;
import com.paly.service.SpecialtyService;
import com.paly.service.StudentService;
import com.paly.service.SubsectionService;
import com.paly.service.UserService;
import com.paly.util.Common;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
public class ServiceTest {
	@Resource
	private UserService userService;
	@Resource
	private SectionService sectionService;
	@Resource
	private SubsectionService subsectionService;
	@Resource
	private ItempoolService itempoolService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private SpecialtyService specialtyService;
	@Resource
	private ClassesService classesService;
	@Resource
	private StudentService studentService;
	@Resource
	private ExamswitchService examswitchService;
	@Resource
	private ScoreService scoreService;
	@Resource
	private RoleService roleService;
	@Resource
	private MenuService menuService;

	@Test
	public void test() {
		User user = new User();
		user.setUserId(10086);
		user.setUserName("aaa" + new Date().getDay());
		user.setUserPassword("bbb");
		userService.save(user);
	}

	@Test
	public void saveSectionTest() throws IOException {
		Section section = new Section();
		section.setSectionChecked(false);
		section.setSectionId(1);
		section.setSectionName("aaa");
		section.setSectionCode(1);
		section.setSectionChecked(true);

		Section section2 = new Section();
		section2.setSectionChecked(false);
		section2.setSectionId(2);
		section2.setSectionName("bbb");
		section2.setSectionCode(2);
		section2.setSectionChecked(true);

		Section section3 = new Section();
		section3.setSectionChecked(false);
		section3.setSectionId(3);
		section3.setSectionName("ccc");
		section2.setSectionCode(3);
		section3.setSectionChecked(true);

		sectionService.save(section);
		sectionService.save(section2);
		sectionService.save(section3);
		List<Section> list = new ArrayList<Section>();
		list.add(section3);
		list.add(section2);
		list.add(section);
	}

	@Test
	public void saveSubSectionTest() throws IOException {
		File file = ResourceUtils.getFile("classpath:content-test.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder content = new StringBuilder();
		String line = "";
		while ((line = reader.readLine()) != null) {
			content.append(line);
		}
		reader.close();
		List<Section> list = sectionService.findAll();
		for (int i = 1; i <= 20; i++) {
			Subsection subsection = new Subsection();
			if (i < (20 / 3)) {
				subsection.setSection(list.get(0));
			} else if (i > (20 / 3) && i < (40 / 3)) {
				subsection.setSection(list.get(1));
			} else {
				subsection.setSection(list.get(2));
			}
			subsection.setSubsectionChecked(false);
			subsection.setSubsectionContent(content.toString());
			subsection.setSubsectionId(i);
			subsection.setSubsectionName("ccc" + i);
			subsection.setSubsectionTime(20);
			subsection.setSubsectionCode(i);
			subsection.setSubsectionChecked(true);
			subsectionService.save(subsection);
		}
	}

	@Test
	public void saveItemPoolTest() {
		for (int i = 0; i < 100; i++) {
			Itempool itempool = new Itempool();
			itempool.setItempoolQuestion("有一段java应用程序，它的主类名是a1，那么保存 它的源文件名可以是？");
			itempool.setA("A.a1.java");
			itempool.setB("B.a.class");
			itempool.setC("C.a1");
			itempool.setD("D.都可以");
			itempool.setItempoolCorrect("A");
			itempool.setItempoolChecked(true);
			itempoolService.save(itempool);
		}
	}

	// public void saveSpe
	@Test
	public void saveDepartmentTest() {
		for (int i = 0; i < 5; i++) {
			Department department = new Department();
			department.setDepartmentName("数计院" + i);
			departmentService.save(department);
		}
	}

	@Test
	public void saveSpecialtyTest() {
		List<Department> departments = departmentService.findAll();
		for (int i = 0; i < 10; i++) {
			Specialty specialty = new Specialty();
			specialty.setDepartment(departments.get(i % 5));
			specialty.setSpecialtyName("软件工程" + i);
			specialtyService.save(specialty);
		}
	}

	@Test
	public void saveClassesTest() {
		List<Specialty> specialtys = specialtyService.findAll();
		for (int i = 0; i < 20; i++) {
			Classes classes = new Classes();
			classes.setClassesName("软件工程" + i + "班");
			classes.setSpecialty(specialtys.get(i % 10));
			classesService.save(classes);
		}
	}

	@Test
	public void saveStudentAndUserAndScoreTest() {
		List<Classes> classes = classesService.findAll();
		for (int i = 0; i < 100; i++) {
			Student student = new Student();
			student.setClasses(classes.get(i % 20));
			student.setGrade("2013");
			student.setStudentEmail("www.1008611@qq.com");
			student.setStudentName("张三" + i);
			student.setStudentNumber("100861" + i);
			student.setStudentSex(i % 2 == 0 ? "男" : "女");
			User user = new User();
			user.setUserName(student.getStudentNumber());
			user.setUserPassword("123456");
			// 添加默认的成绩
			Score score = new Score();
			score.setScoreMakeupNum(0);
			score.setScoreMark((float) 0);
			userService.save(user);
			user = userService.getByUsernameAndPassword(user.getUserName(), "123456");
			student.setUser(user);
			studentService.save(student);
			student = studentService.selectByStudentNumber(student.getStudentNumber());
			score.setStudent(student);
			scoreService.save(score);
		}
	}

	@Test
	public void saveExamswitchTest() {
		Examswitch examswitch = new Examswitch();
		examswitch.setSwitchOnOrOff(false);
		examswitchService.save(examswitch);
	}

	@Test
	public void updateExamswitchTest() {
		Examswitch examswitch = examswitchService.getExamswitch();
		examswitch.setSwitchOnOrOff(!examswitch.getSwitchOnOrOff());
		examswitchService.update(examswitch);
	}

	@Test
	public void saveAdminRoleMenu() {
		Menu menu, menu1, menu2, menu3,menu4;
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
		
		//章节管理
		Menu chapter1 = new Menu("chapterManager", "/chapter", menu1);
		chapter1.setMenuId(6);
		Menu chapter2 = new Menu("sectionController", "/sectionController", menu1);
		chapter2.setMenuId(7);
		menuService.save(chapter1);
		menuService.save(chapter2);
		
		//题库管理
		Menu questionbank = new Menu("questionbankManager", "/questionbank", menu2);
		questionbank.setMenuId(8);
		menuService.save(questionbank);
		
		//报表查看
		Menu report = new Menu("reportManager", "/report", menu3);
		report.setMenuId(9);
		menuService.save(report);
		Menu report2 = new Menu("reportPrintManager", "/reportPrint", menu3);
		report2.setMenuId(10);
		menuService.save(report2);
		
		//学生信息
		Menu adminuser = new Menu("adminuserManager", "/adminuser", menu);
		adminuser.setMenuId(11);
		Menu switchController = new Menu("switchController", "/switchController", menu);
		switchController.setMenuId(12);
		menuService.save(adminuser);
		menuService.save(switchController);
		
		//应用监控
		Menu applicationMonitoring = new Menu("applicationMonitoring","/druid",menu4);
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
		
		//设置普通管理员的角色
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
		
		//设置运维管理员
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
	
	@Test
	public void StringTest(){
		System.out.println(User.class.getClassLoader().getResource("").getPath());
	}
}
