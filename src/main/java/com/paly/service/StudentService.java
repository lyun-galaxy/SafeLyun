package com.paly.service;

import java.util.List;

import com.paly.domain.Student;

/**
 * 学生Service接口
 * @author linyu
 *
 */
public interface StudentService extends BaseService<Student> {
	
	/**
	 * 根据班级id获取其下的学生列表
	 * @param classesId 班级id
	 * @return 返回指定班级下的所有学生列表
	 */
	List<Student> getByClassesId(int classesId);
}