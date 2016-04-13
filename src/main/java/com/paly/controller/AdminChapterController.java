package com.paly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paly.domain.Section;
import com.paly.pageModel.Chapter;
import com.paly.pageModel.Datagrid;
import com.paly.pageModel.Json;
import com.paly.service.AdminChapterService;
/**
 * 
 * @author ron
 *
 */
@Controller
public class AdminChapterController extends AdminBaseController{

	@Resource
	AdminChapterService adminChapterService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminQuestionBankController.class);
	
	@RequestMapping("/chapter/add.action")
	public void add(Chapter chapter,HttpServletResponse response) {

		Json json = new Json();
		try {
			logger.info("add");   
			adminChapterService.save(chapter);
			json.setSuccess(true);
			json.setMsg("添加成功！");
			

		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}

		super.writeJson(json,response);

	}

	@RequestMapping("/chapter/datagridUnaudit.action")
	public void datagridUnaudit(Chapter chapter,HttpServletResponse response) {
		logger.info("datagrid");
 		Datagrid dg = adminChapterService.datagrid(chapter);
		super.writeJson(dg,response);
		System.out.println(chapter.getRows()+"|"+chapter.getPage()+"=========");
	}
	
	@RequestMapping("/chapter/datagridAudit.action")
	public void datagridAudit(Chapter chapter,HttpServletResponse response) {
		logger.info("datagrid");
 		Datagrid dg = adminChapterService.datagrid(chapter);
		super.writeJson(dg,response);
		System.out.println(chapter.getRows()+"|"+chapter.getPage()+"=========");
	}

	@RequestMapping("/chapter/remove.action")
	public void remove(String ids,HttpServletResponse response) {
		Json json = new Json();
		try {
			//int n = chapterService.remove(model.getIds());
			int n = adminChapterService.remove(ids);		
			json.setSuccess(true);
			json.setMsg("成功删除" + n + "条记录!");

		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}

		super.writeJson(json,response);
	}

	@RequestMapping("/chapter/edit.action")
	public void edit(Section section,HttpServletResponse response) {

		Json json = new Json();
		try {
			adminChapterService.edit(section);
			Chapter chapter2 = new Chapter();
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(chapter2);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		super.writeJson(json,response);
	}
	
	@RequestMapping()
	public void audit(String ids,HttpServletResponse response){
		Json json = new Json();
		try {		
			adminChapterService.audit(ids);
			System.out.println("====="+ids);
			json.setSuccess(true);
			json.setMsg("修改成功！");		
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		super.writeJson(json,response);
	}
}
