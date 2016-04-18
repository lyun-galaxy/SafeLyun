package com.paly.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.paly.domain.Student;
import com.paly.mapper.ClassesMapper;
import com.paly.mapper.DepartmentMapper;
import com.paly.mapper.SpecialtyMapper;
import com.paly.mapper.StudentMapper;
import com.paly.pageModel.Clasz;
import com.paly.pageModel.Depart;
import com.paly.pageModel.Grade;
import com.paly.pageModel.Pro;
import com.paly.pageModel.Report;
import com.paly.pageModel.Score;
import com.paly.service.AdminReportService;
import com.paly.vo.StudentVo;

@Service
public class AdminReportServiceImpl implements AdminReportService {

	@Resource
	StudentMapper studentMapper;
	@Resource
	DepartmentMapper departmentMapper;
	@Resource
	SpecialtyMapper specialtyMapper;
	@Resource
	ClassesMapper classesMapper;
	
	private final Logger logger = LoggerFactory.getLogger(AdminReportServiceImpl.class);
	
	public List<Grade> getGrade(){	
    	List<Grade> l = new ArrayList<Grade>();
		Date date = new Date();
		int currentYear = date.getYear();
		Grade grade = null;	
		Grade g = new Grade(0, "20"+String.valueOf(currentYear).substring(1, 3), true);
		l.add(g);
		for(int i = 1; i < 10; i++){
			int myYear = currentYear -i;
			grade = new Grade(i,"20"+String.valueOf(myYear).substring(1, 3),false);
			l.add(grade);
		}		
		return l;
    }

	@Override
	public List<Depart> getDepart() {
		// TODO Auto-generated method stub
		List<Depart> lists = new ArrayList<Depart>();
		Depart d1 = new Depart(1, "信息工程学院");
		Depart d2 = new Depart(2, "教育与科学学院");
		Depart d3 = new Depart(3, "物理与机电工程学院");
		Depart d4 = new Depart(4, "艺术系");
		lists.add(d1);
		lists.add(d2);
		lists.add(d3);
		lists.add(d4);
		return lists;
	}

	@Override
	public List<Pro> getPro() {
		// TODO Auto-generated method stub
		List<Pro> list = new ArrayList<Pro>();
		Pro p = new Pro(1, "数学");
		Pro p1 = new Pro(2, "软件工程");
		Pro p2 = new Pro(3, "计算机");
		list.add(p);
		list.add(p1);
		list.add(p2);
		return list;
	}

	@Override
	public List<Clasz> getClasz() {
		// TODO Auto-generated method stub
		List<Clasz> list = new ArrayList<Clasz>();
		Clasz c = new Clasz(1, "软件工程1班");
		Clasz c2 = new Clasz(2, "软件工程2班");
		list.add(c);
		list.add(c2);
		return list;
	}

	@Override
	public List<Report> getAllDepartDateList(String grade,String departmentName) {
		// TODO Auto-generated method stub
		
		int stuNum = departmentMapper.queryStuCount(grade, departmentName);
		int passNum = departmentMapper.queryPassExamStuCount(grade, departmentName);
		int passRate = 0;
		try {
			passRate = passNum/stuNum;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("stuNum0");
		}
		
		logger.info("==="+ stuNum +"===" +passNum +"==");
		List<Report> list = new ArrayList<Report>();
		Report r = new Report();
		r.setGrade(grade);
		r.setDepartName(departmentName);
		r.setCompleteRate(0.8);  //等待数据库数据
		r.setPassRate(passRate);
		list.add(r);
		return list;
	}

	@Override
	public List<Report> getProDateList(String grade, String departmentName,String specialtyName) {
		// TODO Auto-generated method stub
		int stuNum = specialtyMapper.queryStuCount(grade, departmentName, specialtyName);
		int passNum = specialtyMapper.queryPassExamStuCount(grade, departmentName, specialtyName);
		logger.info(stuNum+"==="+passNum);
		int passRate = 0;
		try {
			passRate = passNum/stuNum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("0");
		}
		
		List<Report> l = new ArrayList<Report>();
		Report r = new Report();
		r.setGrade(grade);
		r.setDepartName(departmentName);
		r.setProName(specialtyName);
		r.setCompleteRate(0.8);
		r.setPassRate(passRate);
		l.add(r);		
		return l;
	}

	@Override
	public List<Score> getClassDateList(String grade, String departmentName,String specialtyName, String classesName) {
		// TODO Auto-generated method stub
		List<StudentVo> students = classesMapper.queryClassesReport(grade, departmentName, specialtyName, classesName);
		StudentVo studentvo = new StudentVo();
		for (int i = 0; i < students.size(); i++) {
			studentvo = students.get(i);
		}
		
		logger.info(studentvo.getStudentNumber()+"******");
		logger.info(studentvo.getStudentName()+"******");
		logger.info(studentvo.getScoreMark()+"******");
		
		List<Score> list = new ArrayList<Score>();
		Score s = new Score(studentvo.getStudentNumber(),studentvo.getStudentName(), studentvo.getScoreMark());
		
		list.add(s);
		
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllStudentScore(String grade) {
		// TODO Auto-generated method stub
		List<Object> studentList = new ArrayList<Object>();
		studentList = studentMapper.queryScoreByGrade("2013");
		for (int i = 0; i < studentList.size(); i++) {
			Student student = (Student) studentList.get(i);
			logger.info(student.getStudentName()+"=====____+++");
		}
		
		Map<String, Object> map=new HashMap<String,Object>();
    	map.put("no", "2013034500");
    	map.put("name", "kuker");
    	map.put("departName", "信息工程学院");
    	map.put("proName", "软件工程");
    	map.put("clazzName", "1班");
    	map.put("score", 66);
    	
    	List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
    	list.add(map);
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllStudentNoPass(String grade) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String,Object>();
    	map.put("no", "2013034500");
    	map.put("name", "haha");
    	map.put("departName", "信息工程学院");
    	map.put("proName", "软件工程");
    	map.put("score", 50);
    	
    	List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
    	list.add(map);
		return list;
	}
}
