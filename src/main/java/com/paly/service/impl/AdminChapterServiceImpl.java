package com.paly.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
		section.setSectionCode(chapter.getCode());
		section.setSectionChecked(false);
		
		super.save(section);
		
	}

	@Override
	public Datagrid datagridAudit(Chapter chapter) {
		// TODO Auto-generated method stub
		PageHelper.startPage(chapter.getPage(), chapter.getRows());
		List<Section> sections = sectionMapper.queryIsChecked();
		PageInfo<Section> page = new PageInfo<Section>(sections);
		
		Datagrid datagrid = new Datagrid();
		datagrid.setRows(sections);
		datagrid.setTotal(page.getTotal());
		return datagrid;
	}

	@Override
	public Datagrid datagridUnaudit(Chapter chapter) {
		// TODO Auto-generated method stub
		PageHelper.startPage(chapter.getPage(), chapter.getRows());
		List<Section> sections = sectionMapper.queryIsNotChecked();
		PageInfo<Section> page = new PageInfo<Section>(sections);
		
		Datagrid datagrid = new Datagrid();
		datagrid.setRows(sections);
		datagrid.setTotal(page.getTotal());
		return datagrid;
	}
	
	@Override
	public Datagrid vaguedatagrid(Chapter chapter) {
		// TODO Auto-generated method stub
		PageHelper.startPage(chapter.getPage(), chapter.getRows());
		List<Section> sections = sectionMapper.fuzzySearchSection(chapter.getSectionName());
		PageInfo<Section> page = new PageInfo<Section>(sections);		
		Datagrid datagrid = new Datagrid();
		datagrid.setRows(sections);
		datagrid.setTotal(page.getTotal());
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
			Boolean flag = section.getSectionChecked();
			if(flag == false){
			section.setSectionChecked(true);
			}else{
				section.setSectionChecked(false);
			}
			sectionMapper.updateByPrimaryKey(section);
		}
	}

}
