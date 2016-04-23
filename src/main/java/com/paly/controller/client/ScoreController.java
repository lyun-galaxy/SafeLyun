package com.paly.controller.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.paly.domain.Student;
import com.paly.domain.User;
import com.paly.service.StudentService;
import com.paly.vo.StudentScore;

/**
 * 成绩界面Controller
 * 
 * @author luohuaming
 *
 */
@Controller
@RequestMapping("/client_score")
public class ScoreController {
	@Resource
	private StudentService studentService;

	/**
	 * 跳转到成绩界面
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/toScoreUI")
	public ModelAndView toScoreUI(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Student student = null;
		if (user != null) {
			// 获取用户所属学生
			student = studentService.selectByStudentNumber(user.getUserName());
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("student", student);
		modelAndView.setViewName("client/score.jsp");
		return modelAndView;
	}

	/**
	 * 获取班级所有学生的成绩列表
	 * 
	 * @return 封装的json数据
	 */
	@RequestMapping("/getClassesStudentScore")
	@ResponseBody
	public Map<String, List<StudentScore>> getClassesStudentScore(HttpSession session) {
		Map<String, List<StudentScore>> map = new HashMap<String, List<StudentScore>>();
		List<StudentScore> studentScores = new ArrayList<StudentScore>();
		List<Student> students = getClassesStudentList(session);
		for (int i = 0; students != null && i < students.size(); i++) {
			Student student = students.get(i);
			// 通过学生信息封装学生成绩视图包装对象
			StudentScore studentScore = makeStudentScore(student);
			studentScores.add(studentScore);
		}
		Collections.sort(studentScores, new Comparator<StudentScore>() {
			@Override
			// 按照成绩降序排序
			public int compare(StudentScore student1, StudentScore student2) {
				return (int) (student2.getScore() - student1.getScore());
			}
		});
		map.put("studentlist", studentScores);
		return map;
	}

	/**
	 * 通过学生信息封装学生成绩视图包装对象
	 * 
	 * @param student
	 *            学生
	 * @return 学生成绩视图包装对象
	 */
	private StudentScore makeStudentScore(Student student) {
		StudentScore studentScore = new StudentScore();
		studentScore.setDepartment(student.getClasses().getSpecialty().getDepartment().getDepartmentName());
		studentScore.setScore(student.getScore().getScoreMark());
		studentScore.setSpecialties(student.getClasses().getSpecialty().getSpecialtyName());
		studentScore.setStudentName(student.getStudentName());
		studentScore.setClasses(student.getClasses().getClassesName());
		studentScore.setStudentNumber(student.getStudentNumber());
		return studentScore;
	}

	/**
	 * 获取当前用户所在班级的所有学生列表
	 * 
	 * @param session
	 *            session域对象，获取域中数据
	 * @return 学生列表
	 */
	private List<Student> getClassesStudentList(HttpSession session) {
		List<Student> students = new ArrayList<Student>();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			// 获取用户所属的学生
			Student student = studentService.selectByStudentNumber(user.getUserName());
			if (student != null) {
				// 获取当前学生所在班级下的学生列表
				students = studentService.getByClassesId(student.getClasses().getClassesId());
			}
		}
		return students;
	}
}
