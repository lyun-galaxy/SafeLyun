package com.paly.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import com.paly.domain.Itempool;
import com.paly.pageModel.Question;

public class POITest {
	@Test
	public void getQuestion(){
		List<Question> questionList = new ArrayList<Question>();
		
		Question question = null;
		Itempool itempool = null;
		File questionFile = new File("G:\\题库模板.xls");
		
		Workbook wk = null;
		try {
			wk = new WorkbookFactory().create(questionFile);
		} catch (InvalidFormatException e) {
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
			
			
			
		}
		
	}

	
}
