package com.paly.service;

import com.paly.domain.Studyschedule;

/**
 * 学期进度Service接口
 * @author luohuaming
 *
 */
public interface StudyscheduleService extends BaseService<Studyschedule> {    
	/**
	 * 根据学生id获取其学习进度信息
	 * @param studentId 学生id
	 * @return 返回指定学生的学习进度
	 */
	Studyschedule getByStudentId(int studentId);
	
	/**
	 * 根据子章节id获取其学习进度信息
	 * @param subsectionId 子章节id
	 * @return 返回指定子章节的学习进度
	 */
	Studyschedule getBySubsectionId(int subsectionId);
}