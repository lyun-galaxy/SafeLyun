package com.paly.controller;

import java.util.ArrayList;
import java.util.List;

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

@Controller
public class AdminReportController extends AdminBaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(AdminReportController.class);
	
	@RequestMapping("/report/getGradeJson.action")
	public void getGradeJson(HttpServletResponse response){
		List<Grade> l = new ArrayList<Grade>();
		Grade g = new Grade(1, "2013", true);
		Grade g1 = new Grade(2, "2014", false);
		Grade g2 = new Grade(3, "2015", false);
		l.add(g);
		l.add(g1);
		l.add(g2);
		super.writeJson(l,response);
	}
	
    @RequestMapping("/report/getDepartJson.action")
    public void getDepartJson(HttpServletResponse response){
    	List<Depart> lists = new ArrayList<Depart>();
		Depart d1 = new Depart(1, "信息工程学院");
		Depart d2 = new Depart(2, "教育与科学学院");
		Depart d3 = new Depart(3, "物理与机电工程学院");
		Depart d4 = new Depart(4, "艺术系");
		lists.add(d1);
		lists.add(d2);
		lists.add(d3);
		lists.add(d4);
		super.writeJson(lists,response);
    }
    
    @RequestMapping("/report/getProJson.action")
    public void getProJson(HttpServletResponse response) {
		List<Pro> list = new ArrayList<Pro>();
		Pro p = new Pro(1, "数学");
		Pro p1 = new Pro(2, "软件工程");
		Pro p2 = new Pro(3, "计算机");
		list.add(p);
		list.add(p1);
		list.add(p2);

		super.writeJson(list,response);
	}
    
    @RequestMapping("/report/getClassJson.action")
	public void getClassJson(HttpServletResponse response) {
		List<Clasz> list = new ArrayList<Clasz>();
		Clasz c = new Clasz(1, "软件工程1班");
		Clasz c2 = new Clasz(2, "软件工程2班");
		list.add(c);
		list.add(c2);
		super.writeJson(list,response);
	}


    @RequestMapping("/report/allDepartDatagrid.action")
	public void allDepartDatagrid(Report report,HttpServletResponse response) {

		logger.info(report.getGradeId()+"======");
		List<Report> list = new ArrayList<Report>();
		Report r = new Report();
		r.setGrade("2013");
		r.setDepartName("信息工程学院");
		r.setCompleteRate(0.8);
		r.setPassRate(0.99);
		Report r1 = new Report();
		r1.setGrade("2013");
		r1.setDepartName("教育与科学学院");
		r1.setCompleteRate(0.9);
		r1.setPassRate(0.89);
		Report r2 = new Report();
		r2.setGrade("2013");
		r2.setDepartName("教育与科学学院");
		r2.setCompleteRate(0.88);
		r2.setPassRate(0.9);
		list.add(r);
		list.add(r1);
		list.add(r2);
		Datagrid d = new Datagrid();
		d.setRows(list);
		d.setTotal((long) list.size());
		super.writeJson(d,response);
	}

    @RequestMapping("/report/proDatagrid.action")
	public void proDatagrid(Report report,HttpServletResponse response) {

		logger.info(report.getGradeId() + "===" + report.getDepartId());

		List<Report> l = new ArrayList<Report>();
		Report r = new Report();
		r.setGrade("2013");
		r.setDepartName("信息工程学院");
		r.setProName("软件工程");
		r.setCompleteRate(0.8);
		r.setPassRate(0.99);
		Report r1 = new Report();
		r1.setGrade("2013");
		r1.setDepartName("信息工程学院");
		r1.setProName("数学");
		r1.setCompleteRate(0.9);
		r1.setPassRate(0.89);
		Report r2 = new Report();
		r2.setGrade("2013");
		r2.setDepartName("信息工程学院");
		r2.setProName("计算机");
		r2.setCompleteRate(0.88);
		r2.setPassRate(0.9);
		l.add(r);
		l.add(r1);
		l.add(r2);
		Datagrid d = new Datagrid();
		d.setRows(l);
		d.setTotal((long) l.size());
		super.writeJson(d,response);

	}

    @RequestMapping("/report/classGatagrid.action")
	public void classGatagrid(Report report,HttpServletResponse response) {
		logger.info(report.getGradeId() + "===" + report.getDepartId() + "===" + report.getProId() + "="
				+ report.getClassId());
		List<Score> list = new ArrayList<Score>();
		Score s = new Score("2013034501", "cbsja", 85);
		Score s1 = new Score("2013034502", "bxjas", 97);
		Score s2 = new Score("2013034503", "vsad", 84);
		Score s3 = new Score("2013034504", "babds", 80);
		Score s4 = new Score("2013034505", "dasd", 90);
		Score s5 = new Score("2013034506", "gdsja", 93);
		Score s6 = new Score("2013034507", "bdasnb", 92);
		Score s7 = new Score("2013034508", "dbsa", 86);
		Score s8 = new Score("2013034509", "tqwhj", 80);
		Score s9 = new Score("2013034510", "bsab", 86);
		Score s10 = new Score("2013034511", "dnnw", 86);
		Score s12 = new Score("2013034524", "kuker", 86);
		Score s13 = new Score("2013034524", "kuker", 86);
		Score s14 = new Score("2013034524", "kuker", 86);
		Score s15 = new Score("2013034524", "kuker", 86);
		Score s16 = new Score("2013034524", "kuker", 86);
		Score s17 = new Score("2013034524", "kuker", 86);
		list.add(s);
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
		list.add(s6);
		list.add(s7);
		list.add(s8);
		list.add(s9);
		list.add(s10);
		list.add(s12);
		list.add(s13);
		list.add(s14);
		list.add(s15);
		list.add(s16);
		list.add(s17);

		Datagrid d = new Datagrid();
		d.setRows(list);
		d.setTotal((long) list.size());
		super.writeJson(d,response);
	}
}
