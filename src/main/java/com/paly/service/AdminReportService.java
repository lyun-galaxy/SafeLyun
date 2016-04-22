package com.paly.service;
import java.util.List;

import com.paly.pageModel.Clasz;
import com.paly.pageModel.Datagrid;
import com.paly.pageModel.Depart;
import com.paly.pageModel.Grade;
import com.paly.pageModel.Pro;
import com.paly.pageModel.Report;
import com.paly.pageModel.Score;

public interface AdminReportService{
	public List<Grade> getGrade();
	
	public List<Depart> getDepart();
	
	public List<Pro> getPro(String departNo);
	
	public List<Clasz> getClasz(String proNo);
	
	public List<Report> getAllDepartDateList(String grade,String departmentNameId);
	
	public List<Report> getProDateList(String grade, String departmentName,String specialtyName);
	
	public List<Score> getClassDateList(String grade, String departmentName,String specialtyName, String classesName);
	
	public Datagrid getAllStudentScore(String grade,Report report);
	
	public Datagrid getAllStudentNoPass(String grade,Report report);
}
