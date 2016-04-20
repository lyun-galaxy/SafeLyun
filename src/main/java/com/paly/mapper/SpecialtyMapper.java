package com.paly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paly.domain.Specialty;

/**
 * 专业Dao接口
 * @author linyu
 *
 */
public interface SpecialtyMapper extends BaseMapper<Specialty> {

	/**
	 * 根据名称查询专业信息
	 * @param specialtyName 专业的名称
	 * @return 返回专业信息
	 */
	Specialty selectByName(String specialtyName);

	/**
	 * 根据院系id获取其下的所有专业列表
	 * @param departmentId 院系id
	 * @return 返回指定院系下的所有专业列表
	 */
	List<Specialty> getByDepartmentId(int departmentId);
	
	/**
	 * 根据班级id获取其所属的专业
	 * @param classesId 班级id
	 * @return 返回指定班级所属的专业
	 */
	Specialty getByClassesId(int classesId);
	
	/**
	 * 查看同一年级，院系，专业下所有学生的人数
	 * @param grade 年级
	 * @param departmentName 院系
	 * @param specialtyName 专业
	 * @return 同一年级，院系，专业下所有学生的人数
	 */
	int queryStuCount(@Param("grade")String grade, @Param("departmentName")String departmentName,
			@Param("specialtyName")String specialtyName);

	/**
	 * 查看同一年级，院系，专业下所有通过考试的学生的人数
	 * @param grade 年级
	 * @param departmentName 院系
	 * @param specialtyName 专业
	 * @return 同一年级，院系，专业下所有通过考试的学生的人数
	 */
	int queryPassExamStuCount(@Param("grade")String grade, @Param("departmentName")String departmentName,
			@Param("specialtyName")String specialtyName);
	
	/**
	 * 查看同一年级，院系，专业下所有成绩不为空（有成绩）的学生的人数
	 * @param grade 年级
	 * @param departmentName 院系
	 * @param specialtyName 专业
	 * @return 返回同一年级，院系，专业下所有成绩大于的学生的人数
	 */
	int queryStuHasScoreCount(@Param("grade")String grade, @Param("departmentName")String departmentName,
			@Param("specialtyName")String specialtyName);
}