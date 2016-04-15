package com.paly.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.paly.domain.Subsection;
import com.paly.mapper.SectionMapper;
import com.paly.mapper.SubsectionMapper;
import com.paly.pageModel.Datagrid;
import com.paly.pageModel.Section;
import com.paly.service.AdminSectionService;
@Service
public class AdminSectionServiceImpl implements AdminSectionService{

	@Resource
	SectionMapper sectionMapper;
	@Resource
	SubsectionMapper subsectionMapper;
	
	private final Logger logger = LoggerFactory.getLogger(AdminSectionServiceImpl.class);
	
	@Override
	public void add(Section section,String name,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Subsection subsection = new Subsection();
		subsection.setSubsectionName(section.getName());
		try {
			String d = "2016/4/14 00:";
			subsection.setSubsectionTime(new SimpleDateFormat("yyyy/MM/dd HH:mm").parse((d+String.valueOf(section.getMinutes()))));
			System.out.println(subsection.getSubsectionTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String myid = null;
		Cookie[] cookies = request.getCookies(); 
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				logger.info("cookie:" + cookie.getValue());
				myid = cookie.getValue();
			}
		}
		subsection.setSubsectionContent(section.getContext());
		subsection.setSection(sectionMapper.selectByPrimaryKey(Integer.valueOf(myid)));
		subsectionMapper.insert(subsection);
		
	}

	@Override
	public int remove(String ids) {
		// TODO Auto-generated method stub
		String[] nids = ids.split(",");
		for (int i = 0; i < nids.length; i++) {
			int id = Integer.valueOf(nids[i]);
			subsectionMapper.deleteByPrimaryKey(id);
		}
		return nids.length;
	}

	@Override
	public Section edit(Section section) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Datagrid datagridAudit(Section section, String name,HttpServletRequest request) {
		Cookie[] cookies = request.getCookies(); 
		String myid = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				logger.info("cookie:" + cookie.getValue());
				myid = cookie.getValue();
			}
		}
		logger.info("====="+myid);
		PageHelper.startPage(section.getPage(), section.getRows());
		List<Subsection> subSections = subsectionMapper.getBySectionId(Integer.valueOf(myid));
		PageInfo<Subsection> page = new PageInfo<Subsection>(subSections);
		List<Section> sections = new ArrayList<Section>();
		Section mysection = null;
		for (int i = 0; i < subSections.size(); i++) {
			mysection = new Section();
			mysection.setId(String.valueOf(subSections.get(i).getSubsectionId()));
			mysection.setName(subSections.get(i).getSubsectionName());
			mysection.setMinutes(1);
			mysection.setContext(subSections.get(i).getSubsectionContent());
			sections.add(mysection);
		}
		Datagrid datagrid = new Datagrid();
		datagrid.setRows(sections);
		datagrid.setTotal(page.getTotal());
		return datagrid;
	}

	@Override
	public Datagrid datagridUnaudit(Section section, String name,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Cookie[] cookies = request.getCookies(); 
		String myid = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				logger.info("cookie:" + cookie.getValue());
				myid = cookie.getValue();
			}
		}
		logger.info("====="+myid);
		PageHelper.startPage(section.getPage(), section.getRows());
		List<Subsection> subSections = subsectionMapper.getBySectionId(Integer.valueOf(myid));
		PageInfo<Subsection> page = new PageInfo<Subsection>(subSections);
		List<Section> sections = new ArrayList<Section>();
		Section mysection = null;
		for (int i = 0; i < subSections.size(); i++) {
			mysection = new Section();
			mysection.setId(String.valueOf(subSections.get(i).getSubsectionId()));
			mysection.setName(subSections.get(i).getSubsectionName());
			mysection.setMinutes(1);
			mysection.setContext(subSections.get(i).getSubsectionContent());
			sections.add(mysection);
		}
		Datagrid datagrid = new Datagrid();
		datagrid.setRows(sections);
		datagrid.setTotal(page.getTotal());
		return datagrid;
	}

}
