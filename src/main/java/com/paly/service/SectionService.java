package com.paly.service;

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
	 * 获取第一章
	 * @return
	 */
	Section getFirst();
}