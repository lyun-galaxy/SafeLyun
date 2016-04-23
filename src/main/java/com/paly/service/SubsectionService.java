package com.paly.service;

import java.util.List;

import com.paly.domain.Subsection;

/**
 * 子章节Service接口
 * 
 * @author linyu
 *
 */
public interface SubsectionService extends BaseService<Subsection> {
	/**
	 * 根据章节Id获取其下的子章节列表
	 * 
	 * @param sectionId
	 *            章节id
	 * @return 返回指定章节下的子章节列表
	 */
	List<Subsection> getBySectionId(int sectionId);

	/**
	 * 根据学习进度id获取其关联的子章节
	 * 
	 * @param studyscheduleId
	 *            学习进度id
	 * @return 返回指定学习进度的子章节信息
	 */
	Subsection getByStudyscheduleId(int studyscheduleId);

	/**
	 * 获取某个章节下面的第一个小节
	 * 
	 * @param sectionId
	 *            章节id
	 * @return 小节信息
	 */
	Subsection getFirstBySectionId(int sectionId);
}