package com.paly.controller.client;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.paly.domain.Json;
import com.paly.domain.Section;
import com.paly.domain.Subsection;
import com.paly.service.SectionService;
import com.paly.service.SubsectionService;

@Controller
@RequestMapping("/client_study")
public class StudyController {
	@Resource
	private SectionService sectionService;
	@Resource
	private SubsectionService subsectionService;

	/**
	 * 获取所有章节列表
	 * 
	 * @return
	 */
	@RequestMapping("/getSectionList")
	@ResponseBody
	public Json getSectionList() {
		List<Section> sectionList = sectionService.findAll();
		// return new Json(true, "success", grade);
		if (sectionList == null) {
			return new Json(false, "fail", sectionList);
		} else {
			return new Json(true, "success", sectionList);
		}
	}

	@RequestMapping("studyUI/{subsectionId}")
	public ModelAndView studyUI(@PathVariable("subsectionId") Integer subsectionId) {
		ModelAndView modelAndView = new ModelAndView();
		List<Section> sectionList = sectionService.findAll();
		// 获取用户当前学习的子章节 TODO
		Subsection subsection = subsectionService.getById(subsectionId);
		modelAndView.addObject("sectionList", sectionList);
		modelAndView.addObject("subsection", subsection);
		modelAndView.setViewName("../../study");
		return modelAndView;
	}
}
