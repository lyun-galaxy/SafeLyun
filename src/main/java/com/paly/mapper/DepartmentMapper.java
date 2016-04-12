package com.paly.mapper;

import java.util.List;

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
}