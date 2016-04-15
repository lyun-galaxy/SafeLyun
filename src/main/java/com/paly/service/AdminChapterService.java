package com.paly.service;

import com.paly.domain.Section;
import com.paly.pageModel.Chapter;
import com.paly.pageModel.Datagrid;



public interface AdminChapterService extends BaseService<Section>{
	public void save(Chapter chapter);

	public Datagrid datagridAudit(Chapter chapter);
	
	public Datagrid datagridUnaudit(Chapter chapter);

	public int remove(String ids);

	public Section edit(Section section);

	public String getSectionIdByCookieName(String name);
	
	public void audit(String ids);
}
