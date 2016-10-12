package com.paly.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paly.pageModel.Report;

public interface AdminReportPrintService {
	public void getAllStudentScoreToPrint(String grade,HttpServletRequest request,HttpServletResponse response);
	public void getAllStudentNoPassToPrint(String grade,HttpServletRequest request,HttpServletResponse response);
	public void getDepartmentStudentScoreToPrint(Report report,HttpServletRequest request,HttpServletResponse response);
	public void getClassStudentScoreToPrint(Report report,HttpServletRequest request, HttpServletResponse response);
}
