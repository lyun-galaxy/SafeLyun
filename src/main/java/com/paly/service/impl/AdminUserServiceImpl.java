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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.paly.domain.Score;
import com.paly.domain.Student;
import com.paly.domain.User;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.ClassesMapper;
import com.paly.mapper.DatadicItemsMapper;
import com.paly.mapper.DepartmentMapper;
import com.paly.mapper.ScoreMapper;
import com.paly.mapper.SpecialtyMapper;
import com.paly.mapper.StudentMapper;
import com.paly.mapper.UserMapper;
import com.paly.service.AdminUserService;

@Service
public class AdminUserServiceImpl extends BaseServiceImpl<Student> implements AdminUserService {

	private final Logger logger = LoggerFactory.getLogger(AdminUserServiceImpl.class);

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
	@Resource
	DatadicItemsMapper datadicItemsMapper;
	@Resource
	ScoreMapper scoreMapper;

	@Override
	public void importStudentBaseMSG(String path) {
		// TODO Auto-generated method stub
		try {
			Workbook wk = new HSSFWorkbook(new FileInputStream(new File(path)));
			Sheet sheet = wk.getSheetAt(0);
			int rowNo = sheet.getLastRowNum() - sheet.getFirstRowNum();

			List<Map<Integer, String>> msgList = new ArrayList<Map<Integer, String>>();
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
				String studentNumber = msgList.get(i).get(0).toString();
				// 解析学号，获取年级，专业，院系，班级
				String grade = studentNumber.substring(0, 4);
				// String college = studentNumber.substring(4, 5);
				// int departmentId = Integer.valueOf(college);
				// String special = studentNumber.substring(4, 6);
				// int specialId = Integer.valueOf(special);
				String clasz = studentNumber.substring(4, 8);
				int claszId = Integer.valueOf(clasz);
				// 设置默认帐号密码,采用MD5加密
				String userPassword = msgList.get(i).get(0).toString();
				String userPasswordMD5 = DigestUtils.md5DigestAsHex(userPassword.getBytes());

				User user = new User(msgList.get(i).get(0).toString(), userPasswordMD5);
				user.setUserId(Integer.valueOf(studentNumber));
				userMapper.insert(user);
				// 保存学生信息
				Student student = new Student();
				student.setStudentNumber(msgList.get(i).get(0).toString());
				student.setStudentName(msgList.get(i).get(1).toString());
				student.setGrade(grade);
				student.setClasses(classesMapper.selectByPrimaryKey(claszId));
				student.setUser(userMapper.selectByPrimaryKey(Integer.valueOf(studentNumber)));
				super.save(student);
				// 存储默认成绩
				Score score = new Score(0f, 0, studentMapper.selectByStudentNumber(studentNumber));
				score.setScoreId(Integer.valueOf(studentNumber));
				scoreMapper.insert(score);
				// 设置学生默认成绩
				Student myStudent = studentMapper.selectByStudentNumber(studentNumber);
				myStudent.setScore(scoreMapper.selectByPrimaryKey(Integer.valueOf(studentNumber)));
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
