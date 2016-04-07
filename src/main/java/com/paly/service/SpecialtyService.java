package com.paly.service;

import java.util.List;

import com.paly.domain.Specialty;

/**
 * 专业Service接口
 *
 */
public interface SpecialtyService extends BaseService<Specialty> {

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

}