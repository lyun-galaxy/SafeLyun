package com.paly.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.paly.domain.Classes;
import com.paly.domain.Department;
import com.paly.domain.Specialty;
import com.paly.domain.Student;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.ClassesMapper;
import com.paly.mapper.DepartmentMapper;
import com.paly.mapper.SpecialtyMapper;
import com.paly.mapper.StudentMapper;
import com.paly.mapper.UserMapper;
import com.paly.service.AdminUserService;

@Service
public class AdminUserServiceImpl extends BaseServiceImpl<Student> implements AdminUserService{

	@Resource
	StudentMapper studentMapper;
	@Resource
	UserMapper userMapper;
	@Resource
	ClassesMapper classesMapper;
	@Resource
	SpecialtyMapper specialtyMapper;
    @Resource
    DepartmentMapper departmentMapper;
	
	@Override
	public void importStudentBaseMSG(String path) {
		// TODO Auto-generated method stub
		try {
			Workbook wk = new HSSFWorkbook(new FileInputStream(new File(path)));
			Sheet sheet = wk.getSheetAt(0);
			int rowNo = sheet.getLastRowNum() - sheet.getFirstRowNum();
			
			List<Map<Integer, String>> msgList = new ArrayList<Map<Integer,String>>();
			for (int i = 1; i < rowNo; i++) {
				Row row = sheet.getRow(i);
				int cellNo = row.getLastCellNum() - row.getFirstCellNum();
				Map<Integer, String> msg = new HashMap<Integer, String>();
				for (int j = 0; j < cellNo; j++) {
					Cell cell = row.getCell(j);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					msg.put(j, cell.getStringCellValue().toString());
				}
				msgList.add(msg);
			}
			
			for (int i = 0; i < msgList.size(); i++) {
			
				Student student = new Student();
                //student.setStudentId(Integer.valueOf(msgList.get(i).get(0).toString()));
				student.setStudentNumber(msgList.get(i).get(0).toString());
				student.setStudentName(msgList.get(i).get(1).toString());
				student.setUser(userMapper.selectByPrimaryKey(1));
				student.setClasses(classesMapper.selectByPrimaryKey(1));
				super.save(student);
				//获取学号
			    //String studentNumber = msgList.get(i).get(0).toString();
			    //通过数据字典解析学号，获取年级，专业，院系，班级
			    //String grade = studentNumber.substring(0, 3);
			    //String college = studentNumber.substring(4, 5);
			    //String special_class = studentNumber.substring(6, 7);
			     
				//先存储外键
				//存储数据
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public BaseMapper<Student> getBaseMapper() {
		// TODO Auto-generated method stub
		return studentMapper;
	}

}
