package com.paly.service;

import java.util.List;

import com.paly.domain.Subsection;
import com.paly.domain.User;

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
	 * 获取通过审核的子章节数目
	 * 
	 * @return 返回通过审核的子章节数目
	 */
	int getIsCheckedCount();

	/**
	 * 获取某个章节下面的第一个小节
	 * 
	 * @param sectionId
	 *            章节id
	 * @return 小节信息
	 */
	Subsection getFirstBySectionId(int sectionId);
	
	/**
	 * 获取用户正在学习的章节,如果用户没有学习进度，则插入一条学习进度，正在学习的章节为第一小节
	 * @param user 用户
	 * @return 用户已经学习完的章节
	 */
	Subsection getStudySubsectionByUser(User user);
	
	/**
	 * 通过子章节编号获取子章节
	 * @param code 子章节编号
	 * @return 子章节信息
	 */
	Subsection getBySubsectionCode(int code);
	
	/**
	 * 查询经过审核的子章节
	 * @return 返回已审核的子章节列表
	 */
	List<Subsection> queryIsChecked();

	/**
	 * 查询未经过审核的子章节
	 * @return 返回未审核的子章节列表
	 */
	List<Subsection> queryIsNotChecked();
	
	/**
	 * 查询章节下未经过审核的子章节列表
	 * @param sectionId 章节id
	 * @return 返回指定章节下未经审核的子章节列表
	 */
	List<Subsection> queryIsNotCheckedBySecId(int sectionId);
		
	/**
	 * 查询章节下经过审核的子章节列表
	 * @param sectionId 章节id
	 * @return 返回指定章节下经过审核的子章节列表
	 */
	List<Subsection> queryIsCheckedBySecId(int sectionId);
}