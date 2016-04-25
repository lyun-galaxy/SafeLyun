package com.paly.service;

import java.util.List;

import com.paly.domain.Section;

/**
 * 章节Service接口
 * 
 * @author luohuaming
 *
 */
public interface SectionService extends BaseService<Section> {
	/**
	 * 根据子章节id获取其所属的章节信息
	 * 
	 * @param subsectionId
	 *            子章节id
	 * @return 返回指定子章节所属的章节信息
	 */
	Section getBySubsectionId(int subsectionId);


	/**
	 * 通过章节编号获取章节
	 * @param code 章节编号
	 * @return 章节信息
	 */
	Section getBySectionCode(int code);
	
	/**
	 * 查询经过审核的章节 
	 * @return 返回经过审核的章节列表
	 */
	List<Section> queryIsChecked();
	
	/**
	 * 查询未经过审核的章节 
	 * @return 返回未经过审核的章节列表
	 */
	List<Section> queryIsNotChecked();
	
	/**
	 * 模糊查找章节
	 * @param sectionName 章节名
	 * @return 返回查找章节到得章节列表
	 */
	List<Section> fuzzySearchSection(String sectionName);
}