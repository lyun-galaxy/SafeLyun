package com.paly.mapper;

import java.util.List;

import com.paly.domain.Student;
import com.paly.vo.StudentScore;

/**
 * 学生Dao接口
 * @author linyu
 *
 */
public interface StudentMapper extends BaseMapper<Student> {
	
	/**
	 * 根据学号查询学生信息
	 * @param studentNumber 学号
	 * @return 返回指定学号的学生信息
	 */
	Student selectByStudentNumber(String studentNumber);
	
	/**
	 * 根据班级id获取其下的学生列表
	 * @param classesId 班级id
	 * @return 返回指定班级下的所有学生列表
	 */
	List<Student> getByClassesId(int classesId);
	
	/**
	 * 查看同一年级下所有学生成绩
	 * @param grade 年级
	 * @return 返回学生的成绩，学号，姓名，专业，班级，院系
	 */
	List<StudentScore> queryScoreByGrade(String grade);
	
	/**
	 * 查看同一年级下所有未通过学生成绩	
	 * @param grade 年级
	 * @return 返回未通过学生的成绩，学号，姓名，专业，班级，院系
	 */
	List<StudentScore> queryFailScoreByGrade(String grade);
	
	List<StudentScore> queryScoreByLike(String studentNumber);
}
