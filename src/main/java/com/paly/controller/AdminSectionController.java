package com.paly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paly.pageModel.Datagrid;
import com.paly.pageModel.Json;
import com.paly.pageModel.Section;
import com.paly.service.AdminChapterService;
import com.paly.service.AdminSectionService;
/**
 * 
 * @author Royal
 *
 */
@Controller
public class AdminSectionController extends AdminBaseController{

	@Resource
	AdminChapterService adminChapterService;
	@Resource
	AdminSectionService adminSectionService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminSectionController.class);
	private final String COOKIENAME = "zjid";

    @RequestMapping("/sectionController/add.action")
	public void add(Section section,HttpServletResponse response,HttpServletRequest request) {
       
		Json json = new Json();
		try {
			logger.info("add");
			adminSectionService.add(section,COOKIENAME, request);
			json.setSuccess(true);
			json.setMsg("添加成功！");
			json.setObj(section);

		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}

		super.writeJson(json,response);

	}

    @RequestMapping("/sectionController/datagridAudit.action")
	public void datagridAudit(Section section,HttpServletRequest request,HttpServletResponse response) {
		logger.info("datagridAudit");
		
		Datagrid datagrid = adminSectionService.datagridAudit(section,COOKIENAME,request);
 		super.writeJson(datagrid,response);
	}
    
    @RequestMapping("/sectionController/vaguedatagrid.action")
	public void vaguedatagrid(Section section,HttpServletRequest request,HttpServletResponse response) {
		logger.info("datagridAudit");
		
		Datagrid datagrid = adminSectionService.vaguedatagrid(section, COOKIENAME, request);
 		super.writeJson(datagrid,response);
	}
    
    @RequestMapping("/sectionController/datagridUnaudit.action")
 	public void datagridUnaudit(Section section,HttpServletRequest request,HttpServletResponse response) {
 		logger.info("datagridUnaudit");
// 		section.setChapterId(adminSectionService.getChapterIdByCookieName(COOKIENAME));
 		Datagrid datagrid = adminSectionService.datagridUnaudit(section,COOKIENAME,request);
 		super.writeJson(datagrid,response);
 	}
    
    @RequestMapping("/sectionController/remove.action")
	public void remove(String ids,HttpServletResponse response) {
		Json json = new Json();
		try {
			int n = adminSectionService.remove(ids);
			json.setSuccess(true);
			json.setMsg("成功删除" + n + "条记录!");

		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}

		super.writeJson(json,response);
	}
    @RequestMapping("/sectionController/edit.action")
	public void edit(Section section,HttpServletResponse response) {

		Json json = new Json();
		try {
			//model.setChapterId(chapterService.getChapterIdByCookieName(COOKIENAME));
			//Section u = sectionService.edit(model);
			adminSectionService.edit(section);
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(section);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		super.writeJson(json,response);
	}
    
    @RequestMapping("/sectionController/audit.action")
  	public void audit(String ids,HttpServletResponse response) {
  		Json json = new Json();
  		try {
  			int n = adminSectionService.audit(ids);
  			json.setSuccess(true);
  			json.setMsg("审核通过" + n + "条记录!");

  		} catch (Exception e) {
  			json.setMsg(e.getMessage());
  		}

  		super.writeJson(json,response);
  	}
    
}
