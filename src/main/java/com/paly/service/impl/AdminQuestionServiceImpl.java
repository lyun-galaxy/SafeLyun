package com.paly.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.paly.pageModel.Datagrid;
import com.paly.pageModel.Question;
import com.paly.service.AdminQuestionService;

@Service
public class AdminQuestionServiceImpl implements AdminQuestionService {

	   //POI解析exel获得题目
		public List<Question> getQuestion(String path){
			List<Question> questionList = new ArrayList<Question>();
			Question question = null;
			
			File questionFile = new File(path);
			Workbook wk = null;
			try {
				InputStream is = new FileInputStream(questionFile);
				wk = new HSSFWorkbook(is);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Sheet sheet = wk.getSheetAt(0);
			int rowNo = sheet.getLastRowNum() - sheet.getFirstRowNum();
			for(int i = 0; i < rowNo; i++){
				Row row = sheet.getRow(i);
				int cellNo = row.getLastCellNum() - row.getFirstCellNum();
				String[] objectValue = null;
				for(int j = 0; j < cellNo; j++){
					Cell cell = row.getCell(j);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					objectValue[j] = cell.getStringCellValue(); 
				}
				question.setTitle(objectValue[0]);
				question.setA(objectValue[1]);
				question.setB(objectValue[2]);
				question.setC(objectValue[3]);
				question.setD(objectValue[4]);
				question.setAnswer(objectValue[5]);
				question.setCreateDateTime(new Date());
				questionList.add(question);
			}
			return questionList;
		}

		//保存题目
		public Question save(Question question) {
			// TODO Auto-generated method stub
			return null;
		}

		//查询题目
		public Datagrid datagrid(Question question) {
			// TODO Auto-generated method stub
			return null;
		}

		//删除题目
		public int remove(String ids) {
			// TODO Auto-generated method stub
			return 0;
		}

		//更改题目
		public Question edit(Question question) {
			// TODO Auto-generated method stub
            
			return null;
		}

}
