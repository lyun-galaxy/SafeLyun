package com.paly.service;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.paly.pageModel.Clasz;
import com.paly.pageModel.Depart;
import com.paly.pageModel.Grade;
import com.paly.pageModel.Pro;
import com.paly.pageModel.Report;
import com.paly.pageModel.Score;

public interface AdminReportService{
	public List<Grade> getGrade();
	
	public List<Depart> getDepart();
	
	public List<Pro> getPro();
	
	public List<Clasz> getClasz();
	
	public List<Report> getAllDepartDateList(String grade,String departmentName);
	
	public List<Report> getProDateList(String grade, String departmentName,String specialtyName);
	
	public List<Score> getClassDateList(String grade, String departmentName,String specialtyName, String classesName);
	
	public List<Map<String, Object>> getAllStudentScore(String grade);
	
	public List<Map<String, Object>> getAllStudentNoPass(String grade);
}
