package com.paly.controller.client;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.paly.controller.BaseController;
import com.paly.domain.Section;
import com.paly.domain.Subsection;
import com.paly.service.SectionService;
import com.paly.service.SubsectionService;

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
		System.out.println(subsectionId);
		writeJson(subsection, response);
	}

	/**
	 * 跳转到学习界面
	 * @return
	 */
	@RequestMapping("/studyUI")
	public ModelAndView studyUI() {
		ModelAndView modelAndView = new ModelAndView();
		List<Section> sectionList = sectionService.findAll();
		// 获取用户当前学习的子章节 TODO
		Subsection subsection = subsectionService.getById(1);
		modelAndView.addObject("sectionList", sectionList);
		modelAndView.addObject("subsection", subsection);
		modelAndView.setViewName("../../study");
		return modelAndView;
	}
}
