package com.paly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Student;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.StudentMapper;
import com.paly.service.StudentService;
import com.paly.vo.StudentScore;

/**
 * 学生Service实现
 * @author luohuaming
 *
 */
@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService{
	@Resource
	private StudentMapper studentMapper;
	@Override
	public List<Student> getByClassesId(int classesId) {
		return studentMapper.getByClassesId(classesId);
	}

	@Override
	public BaseMapper<Student> getBaseMapper() {
		return studentMapper;
	}

	@Override
	public Student selectByStudentNumber(String studentNumber) {
		return studentMapper.selectByStudentNumber(studentNumber);
	}

	@Override
	public List<StudentScore> queryScoreByGrade(String grade) {
		return studentMapper.queryFailScoreByGrade(grade);
	}

	@Override
	public List<StudentScore> queryFailScoreByGrade(String grade) {
		return studentMapper.queryFailScoreByGrade(grade);
	}

}
