package com.paly.mapper;

import com.paly.domain.Studyschedule;

/**
 * 学习进度Dao接口
 * @author linyu
 *
 */
public interface StudyscheduleMapper extends BaseMapper<Studyschedule> {
    
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