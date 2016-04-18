package com.paly.controller.client;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.session.HttpServletSession;
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
	public void getSubsection(@PathVariable("subsectionId") Integer subsectionId, HttpServletResponse response) {
		Subsection subsection = subsectionService.getById(subsectionId);
		writeJson(subsection, response);
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
		Subsection subsection = getUserSubsection(session);
		//将章节列表添加到界面
		modelAndView.addObject("sectionList", sectionList);
		//将学生正在学习的子章节添加到视图中
		modelAndView.addObject("subsection", subsection);
		modelAndView.setViewName("client/study.jsp");
		return modelAndView;
	}

	/**
	 * 获取用户当前学习的小节
	 * 
	 * @param session
	 * @return 正在学习的小节
	 */
	private Subsection getUserSubsection(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Studyschedule studyschedule = new Studyschedule();
		if (user != null) {
			// 获取用户所属的学生
			Student student = studentService.selectByStudentNumber(user.getUserName());
			if (student != null)
				// 获取学生的学习进度
				studyschedule = studyscheduleService.getByStudentId(student.getStudentId());
		}
		if (studyschedule == null)
			return null;
		// 返回当前学习的小节
		return studyschedule.getSubsection();
	}
}
