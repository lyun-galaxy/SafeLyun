package com.paly.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paly.pageModel.Report;
import com.paly.service.AdminReportPrintService;

@Controller
public class AdminReportPrintController extends BaseController{

	private final Logger log = LoggerFactory.getLogger(AdminReportPrintController.class);
	
	@Resource
	AdminReportPrintService adminReportPrintService;
	
	@RequestMapping("/reportPrint/AllStudent.action")
	public void AllStudent(Report report,HttpServletResponse response,HttpServletRequest request){
//		log.info("======"+grade);
//		Cookie[] cookies = request.getCookies();
//		String mygrade = null;
//		for (Cookie cookie : cookies) {			
//		  if(cookie.getName().equals("gradeId")){
//			mygrade = cookie.getValue();
//		  }
//		}
		
        adminReportPrintService.getAllStudentScoreToPrint(report.getGradeId(), request, response);
	
	}
	
	@RequestMapping("/reportPrint/AllNoPassStudent.action")
	public void AllNoPassStudent(Report report,HttpServletResponse response,HttpServletRequest request){
//		Cookie[] cookies = request.getCookies();
//		String mygrade = null;
//		for (Cookie cookie : cookies) {			
//		  if(cookie.getName().equals("gradeId")){
//			mygrade = cookie.getValue();
//		  }
//		}
		
        adminReportPrintService.getAllStudentNoPassToPrint(report.getGradeId(), request, response);
	}
}
