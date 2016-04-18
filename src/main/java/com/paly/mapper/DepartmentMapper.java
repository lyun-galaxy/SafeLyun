package com.paly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paly.domain.Department;

/**
 * 院系Dao接口<br>
 * 当查到一个院系后，其下的专业列表也根据需要会被查出来
 * @author linyu
 *
 */
public interface DepartmentMapper extends BaseMapper<Department> {
   	
	/**
	 * 根据名称查询院系信息
	 * @param departmentName 院系名称
	 * @return 返回指定院系名称的记录
	 */
	Department selectByName(String departmentName);
	
	/**
	 * 批量添加院系
	 * @param departments 院系列表
	 */
	void batchAdd(List<Department> departments);
	
	/**
	 * 根据专业id获取其所属的院系
	 * @param specialtyId 专业id
	 * @return 返回指定专业所属的院系
	 */
	Department getBySpecialtyId(int specialtyId);
	
	/**
	 * 查看同一年级，院系下所有学生的人数
	 * @param grade 年级
	 * @param departmentName 院系
	 * @return 返回同一年级，院系下所有学生的人数
	 */
	int queryStuCount(@Param("grade")String grade, @Param("departmentName")String departmentName);
	
	/**
	 * 查看同一年级，院系下所有通过考试的学生的人数
	 * @param grade 年级
	 * @param departmentName 院系
	 * @return 返回同一年级，院系下所有通过考试的学生的人数
	 */
	int queryPassExamStuCount(@Param("grade")String grade, @Param("departmentName")String departmentName);
	
	/**
	 * 查看同一年级，院系下所有成绩不为空（有成绩）的学生的人数
	 * @param grade 年级
	 * @param departmentName 院系
	 * @return 返回同一年级，院系下所有成绩不为空（有成绩）的学生的人数
	 */
	int queryStuHasScoreCount(@Param("grade")String grade, @Param("departmentName")String departmentName);
}