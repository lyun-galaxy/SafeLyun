package com.paly.service;

import java.util.List;

import com.paly.domain.Itempool;

/**
 * 题库Service接口
 * 
 * @author luohuaming
 *
 */
public interface ItempoolService extends BaseService<Itempool> {
	/**
	 * 随机获取选择题
	 * 
	 * @param count
	 *            数量
	 * @return 题库列表
	 */
	List<Itempool> randomCreateChoiceExams(int count);
}