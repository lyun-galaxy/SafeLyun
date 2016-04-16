package com.paly.service;

import com.paly.domain.Examswitch;

/**
 * 考试开关Service接口
 * @author luohuaming
 */
public interface ExamswitchService extends BaseService<Examswitch> {
	/**
	 * 获取考试开关 
	 * @return
	 */
	Examswitch getExamswitch();
}