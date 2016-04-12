package com.paly.domain;

import java.io.Serializable;

/**
 * 成绩
 * @author linyu
 *
 */
public class Score implements Serializable {
   
	private static final long serialVersionUID = 3567258310318471416L;

	private Integer scoreId;

	/**
     * 分数
     */
	private Float scoreMark;

	/**
	 * 补考次数
	 */
	private Integer scoreMakeupNum;

	/**
	 * 与学生关联，属于学生
	 */
	private Student student;
	
	public Score() {
		super();
	}
	
	/**
	 * 带参数实例化成绩
	 * @param scoreMark 分数
	 * @param scoreMakeupNum 补考次数
	 * @param student 所属的学生，不为空
	 */
	public Score(Float scoreMark, Integer scoreMakeupNum, Student student) {
		super();
		this.scoreMark = scoreMark;
		this.scoreMakeupNum = scoreMakeupNum;
		this.student = student;
	}



	public Integer getScoreId() {
		return scoreId;
	}

	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}

	public Float getScoreMark() {
		return scoreMark;
	}

	public void setScoreMark(Float scoreMark) {
		this.scoreMark = scoreMark;
	}

	public Integer getScoreMakeupNum() {
		return scoreMakeupNum;
	}

	public void setScoreMakeupNum(Integer scoreMakeupNum) {
		this.scoreMakeupNum = scoreMakeupNum;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	

}