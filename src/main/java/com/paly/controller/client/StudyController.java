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
		// TODO 重构
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取想要学习的小节
		Subsection wantSubsection = subsectionService.getById(subsectionId);
		int code = wantSubsection.getSubsectionCode();
		Studyschedule studyschedule = getUserStudyschedule(session);
		int num = studyschedule.getStudyscheduleHasNum();
		if (code > (num + 2)) {
			//用户不能跨越多个章节学习 不能学习 前端显示不能学习
			map.put("status", 2);
			writeJson(map, response);
			return;
		} else if (code <= (num+1)) {
			// 如果想要学习的章节是用户之前学过的和正在学习的 用户可以随意学习已经学过的章节
			// 更新session中的数据
			map.put("status", 1);
			session.setAttribute("study_subsection", wantSubsection);
			session.setAttribute("startStudyTime", System.currentTimeMillis());
			map.put("subsection", wantSubsection);
		} else if (code == (num+2)) {
			// 如果当前章节是用户正在学习的下一下节
			
			// 获取用户当前正在学习的小节
			Subsection studySubsection = getStudySubsectionBySession(session);
			if (session.getAttribute("startStudyTime") == null || studySubsection == null) {
				// 开始学习时间为空 或者正在学习的小节为空 非法访问
				map.put("status", 3);
			} else {
				// 当前时间
				long currentTime = System.currentTimeMillis();
				// 获取当前小节学习时间
				long startStudyTime = (long) session.getAttribute("startStudyTime");
				// 计算时间差 分钟
				long differenceTime = (currentTime - startStudyTime) / (1000 * 60);
				// 获取学习小节所需时间
				int needTime = studySubsection.getSubsectionTime();
				if ((int) differenceTime >= needTime) {
					// 如果完成学时
					map.put("status", 1);
					// 更新session中的数据
					session.setAttribute("study_subsection", wantSubsection);
					session.setAttribute("startStudyTime", System.currentTimeMillis());
					Subsection nextSubsection = subsectionService.getBySubsectionCode(studySubsection.getSubsectionCode()+1);
					if(nextSubsection != null){
						//表示未学习完所有的课程
						studyschedule.setSubsection(nextSubsection);
						// 更新学习进度
						studyschedule.setStudyscheduleHasNum(studyschedule.getStudyscheduleHasNum() + 1);
						studyscheduleService.update(studyschedule);
					}
					map.put("subsection", wantSubsection);
				} else {
					// 还没学习完
					map.put("status", 2);
				}
			}
		}
		writeJson(map, response);
	}

	/**
	 * 从session域中获取当前用户的学习进度
	 * 
	 * @param session
	 * @return
	 */
	private Studyschedule getUserStudyschedule(HttpSession session) {
		Studyschedule studyschedule;
		if (session.getAttribute("studyschedule") == null) {
			// 如果session域中没有数据，从数据库中加载
			User user = (User) session.getAttribute("user");
			Student student = studentService.selectByStudentNumber(user.getUserName());
			studyschedule = studyscheduleService.getByStudentId(student.getStudentId());
			session.setAttribute("studyschedule", studyschedule);
		} else {
			studyschedule = (Studyschedule) session.getAttribute("studyschedule");
		}
		return studyschedule;
	}

	/**
	 * 判断是否学习完成
	 */
	@RequestMapping("isLearningFinish")
	public void isLearningFinish(HttpSession session) {
		// TODO 判断是否学习完成
		// 获取用户当前正在学习的小节
		Subsection studySubsection = getStudySubsectionBySession(session);
		//获取当前用户的学习进度
		Studyschedule studyschedule = getUserStudyschedule(session);
		int code = studySubsection.getSubsectionCode();
		int num = studyschedule.getStudyscheduleHasNum();
		if(code != (num+1)){
			//如果当前章节不是是用户正需要学习的章节， 返回
			return;
		}
		// 当前时间
		long currentTime = System.currentTimeMillis();
		long startStudyTime = (long) session.getAttribute("startStudyTime");
		// 计算时间差 分钟
		long differenceTime = (currentTime - startStudyTime) / (1000 * 60);

		// 获取学习小节所需时间
		int needTime = studySubsection.getSubsectionTime();
		if ((int) differenceTime >= needTime) {
			Subsection nextSubsection = subsectionService.getBySubsectionCode(studySubsection.getSubsectionCode()+1);
			if(nextSubsection != null){
				//表示未学习完所有的课程
				studyschedule.setSubsection(nextSubsection);
				// 更新学习进度
				studyschedule.setStudyscheduleHasNum(studyschedule.getStudyscheduleHasNum() + 1);
				studyscheduleService.update(studyschedule);
			}
		}
	}

	/**
	 * 获取用户当前正在学习的小节
	 */
	@RequestMapping("getSubsectionByUser")
	public void getSubsectionByUser(HttpServletResponse response, HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		// 获取当前用户正在学习的章节
		Subsection studySubsection = getStudySubsectionBySession(session);
		map.put("status", 1);
		// 更新session中的数据
		session.setAttribute("study_subsection", studySubsection);
		session.setAttribute("startStudyTime", System.currentTimeMillis());
		map.put("subsection", studySubsection);
		writeJson(map, response);
	}

	/**
	 * 跳转到学习界面
	 * 
	 * @return
	 */
	@RequestMapping("/studyUI")
	public ModelAndView studyUI(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		// 获取所有通过审核的章节列表
		List<Section> sectionList = sectionService.queryIsChecked();
		long startStudyTime;
		if (session.getAttribute("startStudyTime") == null) {
			startStudyTime = System.currentTimeMillis();
			session.setAttribute("startStudyTime", startStudyTime);
		}
		Subsection subsection = getStudySubsectionBySession(session);
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
	private Subsection getStudySubsectionBySession(HttpSession session) {
		Subsection subsection;
		if (session.getAttribute("study_subsection") == null) {
			subsection = getStudySubsectionByDB(session);
			session.setAttribute("study_subsection", subsection);
		} else {
			subsection = (Subsection) session.getAttribute("study_subsection");
		}
		return subsection;
	}

	/**
	 * 从数据库中获取用户正在的小节
	 * 
	 * @param session
	 * @return 正在学习的小节
	 */
	private Subsection getStudySubsectionByDB(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return subsectionService.getStudySubsectionByUser(user);
	}
}
