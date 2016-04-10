package com.paly.controller;
import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.paly.pageModel.Datagrid;
import com.paly.pageModel.Question;
import com.paly.service.AdminQuestionService;
import com.paly.vo.Json;
/**
 * 
 * @author Royal
 *
 */

@Controller
public class AdminQuestionBankController extends AdminBaseController{

	private static final Logger logger = LoggerFactory.getLogger(AdminQuestionBankController.class);

	@Resource
	AdminQuestionService adminQuestionService;
	
	//数据库数据显示
	@RequestMapping("/questionbank/auditdatagrid.action")
	public void auditdatagrid(HttpServletResponse response) {
		logger.info("datagrid");
		Datagrid dg = new Datagrid();  //获取数据库题目数据
		super.writeJson(dg,response);
		System.out.println("更新已经过验证题目");
	}
	
	//数据库数据显示
		@RequestMapping("/questionbank/unauditdatagrid.action")
		public void unAuditDatagrid(Question question,HttpServletResponse response) {
			logger.info("datagrid");
			Datagrid dg = adminQuestionService.datagrid(question);  //获取数据库题目数据
			super.writeJson(dg,response);
			System.out.println("更新未经过验证题目");
		}
	

	
	//添加题目
	@RequestMapping("/questionbank/add.action")
	public void add(Question question,HttpServletResponse response){
		
		Json json = new Json();
		try {
			logger.info("add");
			//预留保存题目到数据库
			question = adminQuestionService.save(question);
			json.setSuccess(true);
			json.setMsg("添加成功！");
			json.setObj(question);

		} catch (Exception e) {
			json.setMsg(e.getMessage());  //将保存的数据返回页面进行回显
		}

		super.writeJson(json, response);
		System.out.println("add");
	}
	
	//删除题目
	@RequestMapping("/questionbank/remove.action")
	public void remove(String ids,HttpServletResponse response) {
		Json json = new Json();
		try {
		
			int n = adminQuestionService.remove(ids);
			json.setSuccess(true);
			json.setMsg("成功删除" + n + "条记录!");

		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}

		super.writeJson(json,response);
	}
	
	//修改题目
	@RequestMapping("/questionbank/edit.action")
	public void edit(Question question,HttpServletResponse response){
		Json json = new Json();
		try {
			Question u = adminQuestionService.edit(question);
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(u);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		super.writeJson(json,response);
		System.out.println("edit");
	}
	
	//验证题目
	@RequestMapping("/questionbank/audit.action")
	public void audit(Question question,HttpServletResponse response){
		
		Json json = new Json();
		try {
			adminQuestionService.audit(question.getIds());
			json.setSuccess(true);
			json.setMsg("修改成功！");
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		super.writeJson(json,response);
	}
	
	//批量导入
	@RequestMapping("/questionbank/importfile.action")
	public void importfile(MultipartFile file,HttpServletResponse response,HttpServletRequest request){
	
        Json json = new Json();
        
        CommonsMultipartFile cf= (CommonsMultipartFile)file; 
        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        File f = fi.getStoreLocation(); 
        
		try {
			String path = super.saveUploadFile(request, f, "EMPFILE", "xls");
			logger.info(path);
			adminQuestionService.getQuestion(path);
			json.setSuccess(true);
			json.setMsg("导入成功！");
			
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			System.out.println(e.getMessage());
		}
		
	    super.writeJson(json,response);
	}
	
	
	
}
