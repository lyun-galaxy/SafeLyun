package com.paly.mapper;

import java.util.List;

import com.paly.domain.Classes;

/**
 * 班级Dao接口
 * @author linyu
 *
 */
public interface ClassesMapper extends BaseMapper<Classes>{    
	
	/**
	 * 根据名称查询班级信息
	 * @param classesName 班级名称
	 * @return 返回指定名称的班级信息
	 */
	Classes selectByName(String classesName);
	
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
	
	/**
	 * 根据用户id获取其管理的班级列表
	 * @param userId 用户id
	 * @return 返回指定用户管理的班级列表
	 */
	List<Classes> getByUserId(int userId);
}