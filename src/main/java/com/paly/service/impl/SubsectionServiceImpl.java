package com.paly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Subsection;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.SubsectionMapper;
import com.paly.service.SubsectionService;

/**
 * 子章节Service实现
 * @author luohuaming
 *
 */
@Service("subsectionService")
public class SubsectionServiceImpl extends BaseServiceImpl<Subsection> implements SubsectionService {
	@Resource
	private SubsectionMapper subsectionMapper;

	@Override
	public BaseMapper<Subsection> getBaseMapper() {
		return subsectionMapper;
	}
	@Override
	public List<Subsection> getBySectionId(int sectionId) {
		return subsectionMapper.getBySectionId(sectionId);
	}

	@Override
	public Subsection getByStudyscheduleId(int studyscheduleId) {
		return subsectionMapper.getByStudyscheduleId(studyscheduleId);
	}
}
