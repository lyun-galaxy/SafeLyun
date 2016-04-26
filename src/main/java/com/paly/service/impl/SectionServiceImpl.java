package com.paly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Section;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.SectionMapper;
import com.paly.service.SectionService;

/**
 * 章节Service实现
 * 
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

	@Override
	public Section getBySectionCode(int code) {
		return sectionMapper.querySectionByCode(code);
	}

	@Override
	public List<Section> queryIsChecked() {
		return sectionMapper.queryIsChecked();
	}

	@Override
	public List<Section> queryIsNotChecked() {
		return sectionMapper.queryIsNotChecked();
	}

	@Override
	public List<Section> fuzzySearchSection(String sectionName) {
		return sectionMapper.fuzzySearchSection(sectionName);
	}
}
