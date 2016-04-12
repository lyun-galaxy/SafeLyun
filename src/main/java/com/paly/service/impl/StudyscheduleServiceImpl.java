package com.paly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Studyschedule;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.StudyscheduleMapper;
import com.paly.service.StudyscheduleService;

/**
 * 学习进度Service实现
 * @author luohuaming
 *
 */
@Service("studyscheduleService")
public class StudyscheduleServiceImpl extends BaseServiceImpl<Studyschedule> implements StudyscheduleService{
	@Resource
	private StudyscheduleMapper studyscheduleMapper;
	@Override
	public BaseMapper<Studyschedule> getBaseMapper() {
		return studyscheduleMapper;
	}

}
