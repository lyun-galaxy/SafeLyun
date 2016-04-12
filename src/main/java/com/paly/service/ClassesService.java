package com.paly.service;

import java.util.List;

import com.paly.domain.Classes;

/**
 * 班级Service接口
 * 
 *@author luohuaming
 */
public interface ClassesService extends BaseService<Classes>{    
	
	/**
	 * 根据学生id获取其所属的班级
	 * @param studentId 学生id
	 * @return 返回指定学生所属的班级
	 */
	Classes getByStudentId(int studentId);
	
	/**
	 * 根据专业id获取其下的班级列表
	 * @param specialtyId 专业id
	 * @return 返回指定专业下的所有班级列表
	 */
	List<Classes> getBySpecialtyId(int specialtyId);
}