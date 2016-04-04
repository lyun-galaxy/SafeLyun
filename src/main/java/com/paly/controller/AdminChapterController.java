package com.paly.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paly.pageModel.Chapter;
import com.paly.pageModel.Datagrid;
import com.paly.pageModel.Json;

@Controller
public class AdminChapterController extends AdminBaseController{

	private static final Logger logger = LoggerFactory.getLogger(AdminQuestionBankController.class);
	
	@RequestMapping("/chapter/add.action")
	public void add(Chapter chapter,HttpServletResponse response) {

		Json json = new Json();
		try {
			logger.info("add");

			//Chapter u = chapterService.save(model);
			json.setSuccess(true);
			json.setMsg("添加成功！");
			json.setObj(chapter);

		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}

		super.writeJson(json,response);

	}

	@RequestMapping("/chapter/datagridUnaudit.action")
	public void datagridUnaudit(HttpServletResponse response) {
		logger.info("datagrid");
 		Datagrid dg = new Datagrid();
		super.writeJson(dg,response);
	}
	
	@RequestMapping("/chapter/datagridAudit.action")
	public void datagridAudit(HttpServletResponse response) {
		logger.info("datagrid");
 		Datagrid dg = new Datagrid();
		super.writeJson(dg,response);
	}

	@RequestMapping("/chapter/remove.action")
	public void remove(HttpServletResponse response) {
		Json json = new Json();
		try {
			//int n = chapterService.remove(model.getIds());
			int n = 1;
			json.setSuccess(true);
			json.setMsg("成功删除" + n + "条记录!");

		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}

		super.writeJson(json,response);
	}

	@RequestMapping("/chapter/edit.action")
	public void edit(Chapter chapter,HttpServletResponse response) {

		Json json = new Json();
		try {
			//Chapter u = chapterService.edit(model);
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(chapter);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		super.writeJson(json,response);
	}
}
