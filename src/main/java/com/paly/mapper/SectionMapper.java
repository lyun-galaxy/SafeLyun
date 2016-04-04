package com.paly.mapper;

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
}