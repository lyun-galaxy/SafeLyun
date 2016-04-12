package com.paly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Semester;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.SemesterMapper;
import com.paly.service.SemesterService;

/**
 * 学期Service实现
 * @author luohuaming
 *
 */
@Service("semesterService")
public class SemesterServiceImpl extends BaseServiceImpl<Semester> implements SemesterService{
	@Resource
	private SemesterMapper semesterMapper;
	@Override
	public BaseMapper<Semester> getBaseMapper() {
		return semesterMapper;
	}

}
