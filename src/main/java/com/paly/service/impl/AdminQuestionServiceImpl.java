package com.paly.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.paly.domain.Itempool;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.ItempoolMapper;
import com.paly.pageModel.Datagrid;
import com.paly.pageModel.Question;
import com.paly.service.AdminQuestionService;
/**
 * 
 * @author Royal
 *
 */
@Service
public class AdminQuestionServiceImpl extends BaseServiceImpl<Itempool> implements AdminQuestionService {

	@Resource
	ItempoolMapper itempoolMapper;
	//POI解析exel获得题目	
	public void getQuestion(String path){
		
		Itempool itempool = new Itempool();
		
		try {
			Workbook wk = new HSSFWorkbook(new FileInputStream(new File(path)));
			Sheet sheet = wk.getSheetAt(0);
			int rowNo = sheet.getLastRowNum() - sheet.getFirstRowNum();
			
			List<Map<Integer, String>> values = new ArrayList<Map<Integer, String>>();
			
			for (int i = 1; i < rowNo; i++) {
				Row row = sheet.getRow(i);
				int cellNo = row.getLastCellNum() - row.getFirstCellNum();
				
				Map<Integer, String> map = new HashMap<Integer,String>();
				for (int j = 0; j < cellNo; j++) {
					Cell cell = row.getCell(j);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					map.put(j, cell.getStringCellValue().toString());	
				}	
				values.add(map);
			}
			
			for (int i = 0; i < values.size(); i++) {
				String title = values.get(i).get(0).toString();
				String a = values.get(i).get(1).toString();
				String b =values.get(i).get(2).toString();
				String c =values.get(i).get(3).toString();
				String d =values.get(i).get(4).toString();
				String anser =values.get(i).get(5).toString();
				itempool.setItempoolQuestion(title);
				itempool.setA(a);
				itempool.setB(b);
				itempool.setC(c);
				itempool.setD(d);
				itempool.setItempoolCorrect(anser);
				itempoolMapper.insert(itempool);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


		//保存题目
		public Question save(Question question) {
			// TODO Auto-generated method stub
			Itempool itempool = new Itempool();
			itempool.setItempoolQuestion(question.getTitle());
			itempool.setA(question.getA());
			itempool.setB(question.getB());
			itempool.setC(question.getC());
			itempool.setD(question.getD());
			itempool.setItempoolCorrect(question.getAnswer());
			itempoolMapper.insert(itempool);
			return question;
		}

		//查询题目
		public Datagrid datagrid(Question question) {
			// TODO Auto-generated method stub
			List<Itempool> itempools = itempoolMapper.selectAll();
			List<Question> questions = new ArrayList<Question>();
			Question q=null;
			for(int i = 0;i < itempools.size();i++){
				q=new Question();
				q.setId(String.valueOf(itempools.get(i).getItempoolId()));
				q.setA(itempools.get(i).getA());
				q.setB(itempools.get(i).getB());
				q.setC(itempools.get(i).getC());
				q.setD(itempools.get(i).getD());
				q.setStatus(itempools.get(i).getItempoolChecked());
			    q.setTitle(itempools.get(i).getItempoolQuestion());
				q.setAnswer(itempools.get(i).getItempoolCorrect());
				questions.add(q); 
			}
			Datagrid datagrid = new Datagrid();
			datagrid.setTotal((long)questions.size());
			datagrid.setRows(questions);
			return datagrid;
		}

		//删除题目
		public int remove(String ids) {
			// TODO Auto-generated method stub
			String[] nids = ids.split(",");
			int n = 0;
			for (String string : nids) {
				int id = Integer.valueOf(string);		
				super.delete(id);
				n++;
			}			
			return n;
		}

		//更改题目
		public Question edit(Question question) {
			// TODO Auto-generated method stub
			Itempool itempool = new Itempool();
			itempool.setItempoolId(Integer.valueOf(question.getId()));
			itempool.setItempoolQuestion(question.getTitle());
			itempool.setA(question.getA());
			itempool.setB(question.getB());
			itempool.setC(question.getB());
			itempool.setD(question.getB());
			itempool.setItempoolCorrect(question.getAnswer());
            super.update(itempool);
			return question;
		}


		@Override
		public BaseMapper<Itempool> getBaseMapper() {
			// TODO Auto-generated method stub
			return itempoolMapper;
		}


		@Override
		public void audit(String ids) {
			// TODO Auto-generated method stub
			String[] nids = ids.split(",");
			int n = 0;
			for (String string : nids) {
				int id = Integer.valueOf(string);	
				Itempool itempool = super.getById(id);
				itempool.setItempoolId(id);
				itempool.setItempoolChecked(true);
				super.update(itempool);	
			}
		}

}
