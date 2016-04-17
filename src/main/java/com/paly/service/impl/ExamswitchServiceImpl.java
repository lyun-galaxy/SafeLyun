package com.paly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Examswitch;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.ExamswitchMapper;
import com.paly.service.ExamswitchService;

/**
 * 考试开关Service实现
 * @author luohuaming
 *
 */
@Service("examswitchService")
public class ExamswitchServiceImpl extends BaseServiceImpl<Examswitch> implements ExamswitchService {
	@Resource
	private ExamswitchMapper examswitchMapper;

	@Override
	public BaseMapper<Examswitch> getBaseMapper() {
		return examswitchMapper;
	}
	@Override
	public Examswitch getExamswitch() {
		List<Examswitch> list = examswitchMapper.selectAll();
		return list.get(0);
	}
}
