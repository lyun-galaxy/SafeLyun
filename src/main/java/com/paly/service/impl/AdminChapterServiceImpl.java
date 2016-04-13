package com.paly.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Section;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.SectionMapper;
import com.paly.pageModel.Chapter;
import com.paly.pageModel.Datagrid;
import com.paly.service.AdminChapterService;

@Service
public class AdminChapterServiceImpl extends BaseServiceImpl<Section> implements AdminChapterService{

	@Resource
	SectionMapper sectionMapper;
	@Override
	public BaseMapper<Section> getBaseMapper() {
		// TODO Auto-generated method stub
		return sectionMapper;
	}

	@Override
	public void save(Chapter chapter) {
		// TODO Auto-generated method stub
		Section section = new Section();
		section.setSectionName(chapter.getName());
		super.save(section);
		
	}

	@Override
	public Datagrid datagrid(Chapter chapter) {
		// TODO Auto-generated method stub
		List<Section> sections = sectionMapper.selectAll();
		Datagrid datagrid = new Datagrid();
		datagrid.setRows(sections);
		datagrid.setTotal((long) sections.size());
		return datagrid;
	}

	@Override
	public int remove(String ids) {
		// TODO Auto-generated method stub
	    String[] nids = ids.split(",");
	    for (int i = 0; i < nids.length; i++) {
			super.delete(Integer.valueOf(nids[i]));
		}
		return nids.length;
	}

	@Override
	public Section edit(Section section) {
		// TODO Auto-generated method stub
		super.update(section);
		return null;
	}

	@Override
	public String getSectionIdByCookieName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void audit(String ids) {
		// TODO Auto-generated method stub
		String[] nids = ids.split(",");
		for (int i = 0; i < nids.length; i++) {
			int id = Integer.valueOf(nids[i]);
			Section section = sectionMapper.selectByPrimaryKey(id);
			section.setSectionChecked(true);
			sectionMapper.updateByPrimaryKey(section);
		}
	}

}
