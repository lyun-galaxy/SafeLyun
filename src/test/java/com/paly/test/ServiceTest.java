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
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;

import com.paly.domain.Classes;
import com.paly.domain.Department;
import com.paly.domain.Examswitch;
import com.paly.domain.Itempool;
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
import com.paly.service.ScoreService;
import com.paly.service.SectionService;
import com.paly.service.SpecialtyService;
import com.paly.service.StudentService;
import com.paly.service.SubsectionService;
import com.paly.service.UserService;

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

		Section section2 = new Section();
		section2.setSectionChecked(false);
		section2.setSectionId(2);
		section2.setSectionName("bbb");

		Section section3 = new Section();
		section3.setSectionChecked(false);
		section3.setSectionId(3);
		section3.setSectionName("ccc");

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
			subsection.setSection(list.get(i % 3));
			subsection.setSubsectionChecked(false);
			subsection.setSubsectionContent(content.toString());
			subsection.setSubsectionId(i);
			subsection.setSubsectionName("ccc" + i);
			subsection.setSubsectionTime(20);
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
}
