package com.paly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paly.domain.Department;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.DepartmentMapper;
import com.paly.service.DepartmentService;

/**
 * 院系Service实现
 * @author luohuaming
 *
 */
@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService{
	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public BaseMapper<Department> getBaseMapper() {
		return departmentMapper;
	}

	@Override
	public Department selectByName(String departmentName) {
		return departmentMapper.selectByName(departmentName);
	}

	@Override
	public Department getBySpecialtyId(int specialtyId) {
		return departmentMapper.getBySpecialtyId(specialtyId);
	}

}
