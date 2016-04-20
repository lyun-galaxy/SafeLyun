package com.paly.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.paly.domain.DatadicItems;
import com.paly.mapper.ClassesMapper;
import com.paly.mapper.DatadicItemsMapper;
import com.paly.mapper.DepartmentMapper;
import com.paly.mapper.SpecialtyMapper;
import com.paly.mapper.StudentMapper;
import com.paly.pageModel.Clasz;
import com.paly.pageModel.Datagrid;
import com.paly.pageModel.Depart;
import com.paly.pageModel.Grade;
import com.paly.pageModel.Pro;
import com.paly.pageModel.Report;
import com.paly.pageModel.Score;
import com.paly.service.AdminReportService;
import com.paly.vo.StudentScore;
import com.paly.vo.StudentVo;
/**
 * 
 * @author Royal
 *
 */
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
	@Resource
	DatadicItemsMapper datadicItemsMapper;
	
	private final Logger logger = LoggerFactory.getLogger(AdminReportServiceImpl.class);
	
	//获取近十年年级
	public List<Grade> getGrade(){	
    	List<Grade> l = new ArrayList<Grade>();
		Date date = new Date();
		int currentYear = date.getYear();
		Grade grade = null;	
		Grade g = new Grade(0, "20"+String.valueOf(currentYear).substring(1, 3), true);
		l.add(g);
		for(int i = 1; i < 10; i++){
			int myYear = currentYear -i;
			grade = new Grade(Integer.valueOf("20"+String.valueOf(myYear).substring(1, 3)),"20"+String.valueOf(myYear).substring(1, 3),false);
			l.add(grade);
		}		
		return l;
    }

	//获取所有学院
	@Override
	public List<Depart> getDepart() {
		// TODO Auto-generated method stub
		
		List<DatadicItems> datadicItemsList = datadicItemsMapper.getByGroupCode("0");
		List<Depart> lists = new ArrayList<Depart>();
		DatadicItems datadicItems = null;
		Depart depart = null;
		for (int i = 0; i < datadicItemsList.size(); i++) {
			depart = new Depart(Integer.valueOf(datadicItemsList.get(i).getItemCode()),datadicItemsList.get(i).getItemName());
		    lists.add(depart);
		}

		return lists;
	}

	@Override
	public List<Pro> getPro(String departNo) {
		// TODO Auto-generated method stub
		String myDepartNo = null;
		if(Integer.valueOf(departNo) < 10){
			myDepartNo = "0"+departNo;
		}else{
			myDepartNo = departNo;
		}		
		List<DatadicItems> datadicItemsList = datadicItemsMapper.getByGroupCode(myDepartNo);	
		List<Pro> list = new ArrayList<Pro>();
		Pro p = null;
		for (int i = 0; i < datadicItemsList.size(); i++) {
			p = new Pro(Integer.valueOf(datadicItemsList.get(i).getItemCode()),datadicItemsList.get(i).getItemName());
			list.add(p);
		}
		
		return list;
	}

	@Override
	public List<Clasz> getClasz(String proNo) {
		// TODO Auto-generated method stub
		String myProNo = null;
		if(Integer.valueOf(proNo) < 100){
			myProNo = "0" + proNo;
		}else{
			myProNo = proNo;
		}
		List<DatadicItems> datadicItemsList = datadicItemsMapper.getByGroupCode(myProNo);
		List<Clasz> list = new ArrayList<Clasz>();
		Clasz c = null;
		for (int i = 0; i < datadicItemsList.size(); i++) {
			c = new Clasz(Integer.valueOf(datadicItemsList.get(i).getItemCode()),datadicItemsList.get(i).getItemName());
			list.add(c);
		}
		return list;
	}

	@Override
	public List<Report> getAllDepartDateList(String grade,String departmentNameId) {
		// TODO Auto-generated method stub
		
		String departmentName = datadicItemsMapper.getByGroupCode("0").get(Integer.valueOf(departmentNameId)-1).getItemName();
		int stuNum = departmentMapper.queryStuCount(grade, departmentName);
		int passNum = departmentMapper.queryPassExamStuCount(grade, departmentName);
		int stuNumWithSore = departmentMapper.queryStuHasScoreCount(grade, departmentName);
		double passRate = 0;
		double compeleteRate = 0;
		try {
			passRate = (float)passNum/(float)stuNum;
			compeleteRate =(float)stuNumWithSore/(float)stuNum; 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("stuNum0");
		}
		
		logger.info("==="+ stuNum +"===" +passNum +"==");

		List<Report> list = new ArrayList<Report>();
		Report r = new Report();
		r.setGrade(grade);
		r.setDepartName(departmentName);
		r.setCompleteRate(new BigDecimal(compeleteRate).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
		r.setPassRate(new BigDecimal(passRate).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
		list.add(r);
		return list;
	}

	@Override
	public List<Report> getProDateList(String grade, String departmentNameId,String specialtyNameId) {
		// TODO Auto-generated method stub
		String departmentName = datadicItemsMapper.getByGroupCode("0").get(Integer.valueOf(departmentNameId)-1).getItemName();
		String myProNo = null;
		if(Integer.valueOf(specialtyNameId) < 100){
			myProNo = "0" + specialtyNameId;
		}else{
			myProNo = specialtyNameId;
		}
		String specialtyName = datadicItemsMapper.selectByPrimaryKey(myProNo).getItemName();
		int stuNum = specialtyMapper.queryStuCount(grade, departmentName, specialtyName);
		int passNum = specialtyMapper.queryPassExamStuCount(grade, departmentName, specialtyName);
		int stuNumWithSore = specialtyMapper.queryStuHasScoreCount(grade, departmentName, specialtyName);
		logger.info(stuNum+"==="+passNum+"===="+stuNumWithSore);
		double passRate = 0;
		double compeleteRate = 0;
		try {
			passRate = (float)passNum/(float)stuNum;
			compeleteRate = (float)stuNumWithSore/(float)stuNum;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Report> l = new ArrayList<Report>();
		Report r = new Report();
		r.setGrade(grade);
		r.setDepartName(departmentName);
		r.setProName(specialtyName);
		r.setCompleteRate(new BigDecimal(passRate).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
		r.setPassRate(new BigDecimal(compeleteRate).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
		l.add(r);		
		return l;
	}

	@Override
	public List<Score> getClassDateList(String grade, String departmentNameId,String specialtyNameId, String classesNameId) {
		// TODO Auto-generated method stub
		String departmentName = datadicItemsMapper.getByGroupCode("0").get(Integer.valueOf(departmentNameId)-1).getItemName();
		String myProNo = null;
		if(Integer.valueOf(specialtyNameId) < 100){
			myProNo = "0" + specialtyNameId;
		}else{
			myProNo = specialtyNameId;
		}
		String specialtyName = datadicItemsMapper.selectByPrimaryKey(myProNo).getItemName();
		
		String myClassesNameId = null;
		if(Integer.valueOf(classesNameId) < 1000){
			myClassesNameId = "0" + classesNameId;
		}else{
			myClassesNameId = classesNameId;
		}
		String classesName = datadicItemsMapper.selectByPrimaryKey(myClassesNameId).getItemName();
		
		List<StudentVo> students = classesMapper.queryClassesReport(grade, departmentName, specialtyName, classesName);
		StudentVo studentvo = new StudentVo();
		List<Score> list = new ArrayList<Score>();
		for (int i = 0; i < students.size(); i++) {
			studentvo = students.get(i);	
			Score s = new Score(studentvo.getStudentNumber(),studentvo.getStudentName(), studentvo.getScoreMark());	
			list.add(s);
		}	
		logger.info(studentvo.getStudentNumber()+"******");
		logger.info(studentvo.getStudentName()+"******");
		logger.info(studentvo.getScoreMark()+"******");
		return list;
	}

	@Override
	public Datagrid getAllStudentScore(String grade,Report report) {
		// TODO Auto-generated method stub
		
		List<StudentScore> studentList = new ArrayList<StudentScore>();
		PageHelper.startPage(report.getPage(), report.getRows());
		studentList = studentMapper.queryScoreByGrade(grade);
		PageInfo<StudentScore> page = new PageInfo<StudentScore>(studentList);
		Map<String, Object> map= null;
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < studentList.size(); i++) {
			map = new HashMap<String, Object>();
			map.put("no", studentList.get(i).getStudentNumber());
	    	map.put("name",studentList.get(i).getStudentName());
	    	map.put("departName",studentList.get(i).getDepartment());
	    	map.put("proName", studentList.get(i).getSpecialties());
	    	map.put("clazzName", studentList.get(i).getClasses());
	    	map.put("score", studentList.get(i).getScore());
			StudentScore studentScore = studentList.get(i);
			list.add(map);
			logger.info(studentScore.getStudentName()+"=====____+++");
		}	
		
		Datagrid d = new Datagrid();
		d.setRows(list);
		d.setTotal(page.getTotal());
		return d;
	}

	@Override
	public Datagrid getAllStudentNoPass(String grade,Report report) {
		// TODO Auto-generated method stub
		
		List<StudentScore> studentList = new ArrayList<StudentScore>();
		PageHelper.startPage(report.getPage(), report.getRows());
		studentList = studentMapper.queryFailScoreByGrade(grade);
		PageInfo<StudentScore> page = new PageInfo<StudentScore>(studentList);
		Map<String, Object> map= null;
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		for (int i = 0; i < studentList.size(); i++) {
			map = new HashMap<String, Object>();
			map.put("no", studentList.get(i).getStudentNumber());
	    	map.put("name",studentList.get(i).getStudentName());
	    	map.put("departName",studentList.get(i).getDepartment());
	    	map.put("proName", studentList.get(i).getSpecialties());
	    	map.put("clazzName", studentList.get(i).getClasses());
	    	map.put("score", studentList.get(i).getScore());
			StudentScore studentScore = studentList.get(i);
			list.add(map);
			logger.info(studentScore.getStudentName()+"=====____+++");
		}	
		Datagrid d = new Datagrid();
		d.setRows(list);
		d.setTotal(page.getTotal());
		return d;
	}
}
