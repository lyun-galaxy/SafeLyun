package com.paly.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paly.pageModel.Clasz;
import com.paly.pageModel.Datagrid;
import com.paly.pageModel.Depart;
import com.paly.pageModel.Grade;
import com.paly.pageModel.Pro;
import com.paly.pageModel.Report;
import com.paly.pageModel.Score;
import com.paly.service.AdminReportService;
/**
 * 
 * @author Royal
 *
 */
@Controller
public class AdminReportController extends AdminBaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(AdminReportController.class);
	
	@Resource
	AdminReportService adminReportService;
	
	@RequestMapping("/report/getGradeJson.action")
	public void getGradeJson(HttpServletResponse response){		
		List<Grade> l = adminReportService.getGrade();	
		super.writeJson(l,response);
	}
	
    @RequestMapping("/report/getDepartJson.action")
    public void getDepartJson(HttpServletResponse response){
    	List<Depart> lists = adminReportService.getDepart();
		super.writeJson(lists,response);
    }
    
    @RequestMapping("/report/getProJson.action")
    public void getProJson(String parentid,HttpServletResponse response) {
		List<Pro> list = adminReportService.getPro(parentid);
		super.writeJson(list,response);
	}
    
    @RequestMapping("/report/getClassJson.action")
	public void getClassJson(String parentid,HttpServletResponse response) {
		List<Clasz> list = adminReportService.getClasz(parentid);
		super.writeJson(list,response);
	}


    @RequestMapping("/report/allDepartDatagrid.action")
	public void allDepartDatagrid(Report report,HttpServletResponse response,HttpServletRequest request) {
  	
		logger.info(report.getDepartId()+"======");
		List<Report> list = adminReportService.getAllDepartDateList(report.getGradeId(),report.getDepartId());
		Datagrid d = new Datagrid();
		d.setRows(list);
		d.setTotal((long) list.size());
		super.writeJson(d,response);
	}

    @RequestMapping("/report/proDatagrid.action")
	public void proDatagrid(Report report,HttpServletResponse response,HttpServletRequest request) {

		logger.info(report.getGradeId() + "===" + report.getDepartName());

		List<Report> l = adminReportService.getProDateList(report.getGradeId(),report.getDepartId(),report.getProId());
		Datagrid d = new Datagrid();
		d.setRows(l);
		d.setTotal((long) l.size());
		super.writeJson(d,response);

	}

    @RequestMapping("/report/classGatagrid.action")
	public void classGatagrid(Report report,HttpServletResponse response) {
		logger.info(report.getGradeId() + "===" + report.getDepartName() + "===" + report.getProName() + "="
				+ report.getClassName());
		List<Score> list = adminReportService.getClassDateList(report.getGradeId(),report.getDepartId(),report.getProId(),report.getClassId());
		Datagrid d = new Datagrid();
		d.setRows(list);
		d.setTotal((long) list.size());
		super.writeJson(d,response);
	}
    
    @RequestMapping("/report/allStudentScore.action")
    public void allStudentScore(Report report,HttpServletResponse response,HttpServletRequest request){
    	Cookie[] cookies = request.getCookies();
		String mygrade = null;
		for (Cookie cookie : cookies) {			
		  if(cookie.getName().equals("gradeId")){
			mygrade = cookie.getValue();
		  }
		}
    	logger.info(mygrade+"+++++++");
    	Datagrid d=adminReportService.getAllStudentScore(mygrade,report);
    
		super.writeJson(d,response);
    }
    
    @RequestMapping("/report/allNoPass.action")
    public void allNoPass(Report report,HttpServletResponse response,HttpServletRequest request){
    	
    	Cookie[] cookies = request.getCookies();
		String mygrade = null;
		for (Cookie cookie : cookies) {			
		  if(cookie.getName().equals("gradeId")){
			mygrade = cookie.getValue();
		  }
		}
    	
    	Datagrid d=adminReportService.getAllStudentNoPass(mygrade,report);
    	
		super.writeJson(d,response);
    }
}
