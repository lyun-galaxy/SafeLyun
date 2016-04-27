package com.paly.service;

import java.util.List;

import com.paly.domain.Itempool;
import com.paly.domain.User;

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
	
	/**
	 * 判断用户是否可以考试
	 * @param user 用户
	 * @return 状态：1 可以考试  ，0 不是考试时间 ，2考试已经通过 ，3补考次数已用完
	 */
	int isCanExamByUser(User user);
}