package com.paly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Section;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.SectionMapper;
import com.paly.service.SectionService;

/**
 * 章节Service实现
 * @author luohuaming
 *
 */
@Service("sectionService")
public class SectionServiceImpl extends BaseServiceImpl<Section> implements SectionService {
	@Resource
	private SectionMapper sectionMapper;

	@Override
	public BaseMapper<Section> getBaseMapper() {
		return sectionMapper;
	}

	@Override
	public Section getBySubsectionId(int subsectionId) {
		return sectionMapper.getBySubsectionId(subsectionId);
	}
}