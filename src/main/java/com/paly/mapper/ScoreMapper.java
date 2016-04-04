package com.paly.mapper;

import com.paly.domain.Score;

/**
 * 成绩Dao接口
 * @author linyu
 *
 */
public interface ScoreMapper extends BaseMapper<Score> {

	/**
	 * 根据学生id获取其对应的成绩信息
	 * @param studentId 学生id
	 * @return 返回指定学生的成绩信息
	 */
	Score getByStudentId(int studentId);
  
}