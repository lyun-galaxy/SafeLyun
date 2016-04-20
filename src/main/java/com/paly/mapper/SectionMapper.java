package com.paly.mapper;

import java.util.List;

import com.paly.domain.Section;
/**
 * 章节Dao接口
 * @author linyu
 *
 */
public interface SectionMapper extends BaseMapper<Section> {   
	
	/**
	 * 根据子章节id获取其所属的章节信息
	 * @param subsectionId 子章节id
	 * @return 返回指定子章节所属的章节信息
	 */
	Section getBySubsectionId(int subsectionId);
	
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
