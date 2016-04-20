package com.paly.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminReportPrintService {
	public void getAllStudentScoreToPrint(String grade,HttpServletRequest request,HttpServletResponse response);
	public void getAllStudentNoPassToPrint(String grade,HttpServletRequest request,HttpServletResponse response);
}
