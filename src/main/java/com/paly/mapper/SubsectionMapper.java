package com.paly.mapper;

import java.util.List;

import com.paly.domain.Subsection;

/**
 * 子章节Dao接口
 * @author linyu
 *
 */
public interface SubsectionMapper extends BaseMapper<Subsection> {
   
	/**
	 * 根据章节Id获取其下的子章节列表
	 * @param sectionId 章节id
	 * @return 返回指定章节下的子章节列表
	 */
	List<Subsection> getBySectionId(int sectionId);
}