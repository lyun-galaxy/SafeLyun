package com.paly.controller.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.paly.controller.BaseController;
import com.paly.domain.Section;
import com.paly.domain.Student;
import com.paly.domain.Studyschedule;
import com.paly.domain.Subsection;
import com.paly.domain.User;
import com.paly.service.SectionService;
import com.paly.service.StudentService;
import com.paly.service.StudyscheduleService;
import com.paly.service.SubsectionService;
import com.paly.service.UserService;

/**
 * 在线学习Controller
 * 
 * @author luohuaming
 *
 */
@Controller
@RequestMapping("/client_study")
public class StudyController extends BaseController {
	@Resource
	private SectionService sectionService;
	@Resource
	private SubsectionService subsectionService;
	@Resource
	private UserService userService;
	@Resource
	private StudentService studentService;
	@Resource
	private StudyscheduleService studyscheduleService;

	/**
	 * 获取所有章节列表
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/getSectionList")
	public void getSectionList(HttpServletResponse response) throws IOException {
		List<Section> sectionList = sectionService.findAll();
		writeJson(sectionList, response);
	}

	/**
	 * 根据父章节id获取所有子章节
	 * 
	 * @param sectionId
	 *            父章节id
	 * @param response
	 *            响应头，用于输出json
	 */
	@RequestMapping("getSubsectionList/{sectionId}")
	public void getSubsectionList(@PathVariable("sectionId") Integer sectionId, HttpServletResponse response) {
		List<Subsection> subsections = subsectionService.getBySectionId(sectionId);
		writeJson(subsections, response);
	}

	/**
	 * 根据子章节id获取子章节信息
	 * 
	 * @param sectionId
	 *            子章节id
	 * @param response
	 *            响应头，用于输出json
	 */
	@RequestMapping("getSubsection/{subsectionId}")
	public void getSubsection(@PathVariable("subsectionId") Integer subsectionId, HttpServletResponse response,
			HttpSession session) {
		Subsection subsection = subsectionService.getById(subsectionId);
		writeJson(subsection, response);
	}

	/**
	 * 获取用户当前正在学习的小节
	 */
	@RequestMapping("getSubsectionByUser")
	public void getSubsectionByUser(HttpServletResponse response, HttpSession session) {
		// 获取当前用户正在学习的章节
		Subsection studySubsection = getSubsectionBySession(session);
		writeJson(studySubsection, response);
	}

	/**
	 * 跳转到学习界面
	 * 
	 * @return
	 */
	@RequestMapping("/studyUI")
	public ModelAndView studyUI(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		List<Section> sectionList = sectionService.findAll();
		Subsection subsection = getSubsectionBySession(session);
		// 将章节列表添加到界面
		modelAndView.addObject("sectionList", sectionList);
		// 将学生正在学习的子章节添加到视图中
		modelAndView.addObject("subsection", subsection);
		modelAndView.setViewName("client/study.jsp");
		return modelAndView;
	}

	/**
	 * 在session作用域中获取当前学生学习的小节
	 * 
	 * @param session
	 * @return 当前学生学习的小节
	 */
	private Subsection getSubsectionBySession(HttpSession session) {
		Subsection subsection;

		if (session.getAttribute("user_subsection") == null) {
			subsection = getUserSubsection(session);
			session.setAttribute("user_subsection", subsection);
		} else {
			subsection = (Subsection) session.getAttribute("user_subsection");
		}
		return subsection;
	}

	/**
	 * 从数据库中获取用户当前学习的小节
	 * 
	 * @param session
	 * @return 正在学习的小节
	 */
	private Subsection getUserSubsection(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Studyschedule studyschedule = new Studyschedule();
		Student student = null;
		if (user != null) {
			// 获取用户所属的学生
			student = studentService.selectByStudentNumber(user.getUserName());
			if (student != null)
				// 获取学生的学习进度
				studyschedule = studyscheduleService.getByStudentId(student.getStudentId());
		}
		if (studyschedule == null) {
			// 如果学习进度为空，返回第一章的第一小节，并保存学习进度
			// TODO 获取第一章的第一小节
			Section section = sectionService.getFirst();
			if (section != null) {
				Subsection subsection = subsectionService.getFirstBySectionId(section.getSectionId());
				studyschedule = new Studyschedule(0, subsection, student);
				studyscheduleService.save(studyschedule);
			}
		}
		// 返回当前学习的小节
		return studyschedule.getSubsection();
	}
}
