package com.paly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Student;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.StudentMapper;
import com.paly.service.StudentService;

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

}
