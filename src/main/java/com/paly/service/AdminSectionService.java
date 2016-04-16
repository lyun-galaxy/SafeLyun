package com.paly.service;

import javax.servlet.http.HttpServletRequest;

import com.paly.pageModel.Datagrid;
import com.paly.pageModel.Section;

public interface AdminSectionService {
	public void add(Section section,String name,HttpServletRequest request);
	
	public Datagrid datagridAudit(Section section, String name,HttpServletRequest request);
	
	public Datagrid datagridUnaudit(Section section, String name,HttpServletRequest request);

	public int remove(String ids);

	public Section edit(Section section);
	
	public int audit(String ids);
}
