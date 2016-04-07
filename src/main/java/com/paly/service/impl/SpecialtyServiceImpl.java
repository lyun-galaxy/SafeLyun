package com.paly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Specialty;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.SpecialtyMapper;
import com.paly.service.SpecialtyService;

/**
 * 专业Service实现
 * @author luohuaming
 *
 */
@Service("specialtyService")
public class SpecialtyServiceImpl extends BaseServiceImpl<Specialty> implements SpecialtyService{
	@Resource
	private SpecialtyMapper specialtyMapper;
	@Override
	public Specialty selectByName(String specialtyName) {
		return specialtyMapper.selectByName(specialtyName);
	}

	@Override
	public List<Specialty> getByDepartmentId(int departmentId) {
		return specialtyMapper.getByDepartmentId(departmentId);
	}

	@Override
	public Specialty getByClassesId(int classesId) {
		return specialtyMapper.getByClassesId(classesId);
	}

	@Override
	public BaseMapper<Specialty> getBaseMapper() {
		return specialtyMapper;
	}

}
