package com.paly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Score;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.ScoreMapper;
import com.paly.service.ScoreService;

/**
 * 成绩Service接口实现
 * @author luohuaming
 *
 */
@Service("scoreService")
public class ScoreServiceImpl extends BaseServiceImpl<Score> implements ScoreService{
	@Resource
	private ScoreMapper scoreMapper;
	@Override
	public Score getByStudentId(int studentId) {
		return scoreMapper.getByStudentId(studentId);
	}

	@Override
	public BaseMapper<Score> getBaseMapper() {
		return scoreMapper;
	}

}
