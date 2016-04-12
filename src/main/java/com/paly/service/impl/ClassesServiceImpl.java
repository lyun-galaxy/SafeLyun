package com.paly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paly.domain.Classes;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.ClassesMapper;
import com.paly.service.ClassesService;

@Service("classesService")
public class ClassesServiceImpl extends BaseServiceImpl<Classes> implements ClassesService{
	@Autowired
	private ClassesMapper classesMapper;
	@Override
	public Classes getByStudentId(int studentId) {
		return classesMapper.getByStudentId(studentId);
	}

	@Override
	public List<Classes> getBySpecialtyId(int specialtyId) {
		return classesMapper.getBySpecialtyId(specialtyId);
	}

	@Override
	public BaseMapper<Classes> getBaseMapper() {
		return classesMapper;
	}


}
