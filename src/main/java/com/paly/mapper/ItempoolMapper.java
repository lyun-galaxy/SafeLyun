package com.paly.mapper;

import java.util.List;

import com.paly.domain.Itempool;

/**
 * 题库Dao接口<br>
 * 
 * @author linyu
 *
 */
public interface ItempoolMapper extends BaseMapper<Itempool> {
   
	/**
	 * 获取所有经过审核的题目
	 * @return 返回经过审核的题目列表
	 */
	List<Itempool> getIsChecked();
	
	/**
	 * 获取所有未经过审核的题目
	 * @return 返回未经过审核的题目列表
	 */
	List<Itempool> getIsNotChecked();
	
	/**
	 * 模糊查找题目
	 * @return 返回所有匹配的题目列表
	 */
	List<Itempool> queryByVagueQuestion(String question);
}