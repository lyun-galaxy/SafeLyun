package com.paly.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paly.vo.Json;

@Controller
public class AdminReportPrintController extends BaseController{

	private final Logger log = LoggerFactory.getLogger(AdminReportPrintController.class);
	
	@RequestMapping("/reportPrint/ClassStudent.action")
	public void printClassStudent(String grade,HttpServletResponse response,HttpServletRequest request){
		log.info("======"+grade);
		Cookie[] cookies = request.getCookies();
		String mygrade = null;
		for (Cookie cookie : cookies) {			
		  if(cookie.getName().equals("gradeId")){
			mygrade = cookie.getValue();
		  }
		}
	
		Json json = new Json();
		try {
			log.info("add");
			json.setSuccess(true);
			json.setMsg("打印成功！");
		} catch (Exception e) {
			json.setMsg(e.getMessage());  //将保存的数据返回页面进行回显
		}

		super.writeJson(json, response);
	}
}
