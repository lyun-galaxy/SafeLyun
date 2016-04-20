package com.paly.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.paly.mapper.StudentMapper;
import com.paly.service.AdminReportPrintService;
import com.paly.util.DownloadUtil;
import com.paly.vo.StudentScore;

@Service
public class AdminReportPrintServiceImpl implements AdminReportPrintService {

	@Resource
	StudentMapper studentMapper;


	@Override
	public void getAllStudentScoreToPrint(String grade,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			// 获取数据
			List<StudentScore> studentList = new ArrayList<StudentScore>();

			studentList = studentMapper.queryScoreByGrade(grade);

			// 开始处理报表
			String path = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/make/xlsprint/";

			InputStream is = new FileInputStream(new File(path
					+ "studentScoreTemplate.xls"));
			Workbook wk = new HSSFWorkbook(is);

			Sheet sheet = wk.getSheetAt(0);

			Row nRow = null;
			Cell nCell = null;
			int rowNo = 0;
			int colNo = 1;
			// 获取模板上单元格的样式
			nRow = sheet.getRow(2);
			// 获取模板上学号的样式
			nCell = nRow.getCell(1);
			CellStyle stuNumStyle = nCell.getCellStyle();
			// 获取模板上学号的样式
			nCell = nRow.getCell(2);
			CellStyle stuNameStyle = nCell.getCellStyle();
			// 获取模板上学号的样式
			nCell = nRow.getCell(3);
			CellStyle DepStyle = nCell.getCellStyle();
			// 获取模板上学号的样式
			nCell = nRow.getCell(4);
			CellStyle speStyle = nCell.getCellStyle();
			// 获取模板上学号的样式
			nCell = nRow.getCell(5);
			CellStyle claStyle = nCell.getCellStyle();
			// 获取模板上学号的样式
			nCell = nRow.getCell(6);
			CellStyle scoreStyle = nCell.getCellStyle();
			// 获取模板上学号的样式
			nCell = nRow.getCell(7);
			CellStyle exStyle = nCell.getCellStyle();

			// 处理大标题
			nRow = sheet.getRow(rowNo++);
			nCell = nRow.getCell(colNo);
			nCell.setCellValue(grade + "级安全教育学生成绩表");

			rowNo++; // 跳过静态表头

			// 处理数据
			for (int i = 0; i < studentList.size(); i++) {
				colNo = 1;
				StudentScore studentScore = studentList.get(i);

				nRow = sheet.createRow(rowNo++);
				nRow.setHeightInPoints(24);

				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(studentScore.getStudentNumber());
				nCell.setCellStyle(stuNumStyle);

				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(studentScore.getStudentName());
				nCell.setCellStyle(stuNameStyle);

				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(studentScore.getDepartment());
				nCell.setCellStyle(DepStyle);

				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(studentScore.getSpecialties());
				nCell.setCellStyle(speStyle);

				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(studentScore.getClasses());
				nCell.setCellStyle(claStyle);

				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(studentScore.getScore());
				nCell.setCellStyle(scoreStyle);

				nCell = nRow.createCell(colNo++);
				nCell.setCellValue("");
				nCell.setCellStyle(exStyle);
			}


			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wk.write(os);
			
			DownloadUtil downloadUtil = new DownloadUtil();				//直接弹出下载框，用户可以打开，可以保存
			downloadUtil.download(os, response, grade+"级安全教育学生成绩表.xls");
			
			os.flush();
			os.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void getAllStudentNoPassToPrint(String grade,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			// 获取数据
			List<StudentScore> studentList = new ArrayList<StudentScore>();

			studentList = studentMapper.queryFailScoreByGrade(grade);

			// 开始处理报表
			String path = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/make/xlsprint/";

			InputStream is = new FileInputStream(new File(path
					+ "studentScoreTemplate.xls"));
			Workbook wk = new HSSFWorkbook(is);

			Sheet sheet = wk.getSheetAt(0);

			Row nRow = null;
			Cell nCell = null;
			int rowNo = 0;
			int colNo = 1;
			// 获取模板上单元格的样式
			nRow = sheet.getRow(2);
			// 获取模板上学号的样式
			nCell = nRow.getCell(1);
			CellStyle stuNumStyle = nCell.getCellStyle();
			// 获取模板上学号的样式
			nCell = nRow.getCell(2);
			CellStyle stuNameStyle = nCell.getCellStyle();
			// 获取模板上学号的样式
			nCell = nRow.getCell(3);
			CellStyle DepStyle = nCell.getCellStyle();
			// 获取模板上学号的样式
			nCell = nRow.getCell(4);
			CellStyle speStyle = nCell.getCellStyle();
			// 获取模板上学号的样式
			nCell = nRow.getCell(5);
			CellStyle claStyle = nCell.getCellStyle();
			// 获取模板上学号的样式
			nCell = nRow.getCell(6);
			CellStyle scoreStyle = nCell.getCellStyle();
			// 获取模板上学号的样式
			nCell = nRow.getCell(7);
			CellStyle exStyle = nCell.getCellStyle();

			// 处理大标题
			nRow = sheet.getRow(rowNo++);
			nCell = nRow.getCell(colNo);
			nCell.setCellValue(grade + "级安全教育学生成绩表");

			rowNo++; // 跳过静态表头

			// 处理数据
			for (int i = 0; i < studentList.size(); i++) {
				colNo = 1;
				StudentScore studentScore = studentList.get(i);

				nRow = sheet.createRow(rowNo++);
				nRow.setHeightInPoints(24);

				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(studentScore.getStudentNumber());
				nCell.setCellStyle(stuNumStyle);

				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(studentScore.getStudentName());
				nCell.setCellStyle(stuNameStyle);

				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(studentScore.getDepartment());
				nCell.setCellStyle(DepStyle);

				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(studentScore.getSpecialties());
				nCell.setCellStyle(speStyle);

				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(studentScore.getClasses());
				nCell.setCellStyle(claStyle);

				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(studentScore.getScore());
				nCell.setCellStyle(scoreStyle);

				nCell = nRow.createCell(colNo++);
				nCell.setCellValue("");
				nCell.setCellStyle(exStyle);
			}
		
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wk.write(os);
			
			DownloadUtil downloadUtil = new DownloadUtil();				//直接弹出下载框，用户可以打开，可以保存
			downloadUtil.download(os, response, grade+"级安全教育未通过学生成绩表.xls");
			
			os.flush();
			os.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
