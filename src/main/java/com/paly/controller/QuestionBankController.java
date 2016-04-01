package com.paly.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.eval.StringValueEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.paly.pageModel.Datagrid;
import com.paly.pageModel.Question;
import com.paly.vo.Json;
import com.paly.vo.Tquestion;





@Controller
public class QuestionBankController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(QuestionBankController.class);

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
		public void unAuditDatagrid(HttpServletResponse response) {
			logger.info("datagrid");
			Datagrid dg = new Datagrid();  //获取数据库题目数据
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
	public void remove(Question question,HttpServletResponse response) {
		Json json = new Json();
		try {
			//int n = questionService.remove(model.getIds());
			int n = 1;
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
			//Question u = questionService.edit(model);调用service修改题库
			json.setSuccess(true);
			json.setMsg("修改成功！");
			json.setObj(question);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		super.writeJson(json,response);
		System.out.println("edit");
	}
	
	@RequestMapping("/questionbank/audit.action")
	public void audit(Question question,HttpServletResponse response){
		System.out.println("id:"+question.getIds());
	}
	
	@RequestMapping("/questionbank/importfile.action")
	public void importfile(MultipartFile file,HttpServletResponse response,HttpServletRequest request){
	
        Json json = new Json();
        
        CommonsMultipartFile cf= (CommonsMultipartFile)file; 
        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        File f = fi.getStoreLocation(); 
        
		try {
			String path = super.saveUploadFile(request, f, "EMPFILE", "xls");
			logger.info(path);
			json.setSuccess(true);
			json.setMsg("导入成功！");
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		
	    super.writeJson(json,response);
	}
	
	
	
}
