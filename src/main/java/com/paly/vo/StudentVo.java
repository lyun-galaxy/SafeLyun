package com.paly.vo;

public class StudentVo {
	
	/**
	 * @author Royal
	 */
	private Integer studentId;
    private String studentNumber;
    private String studentName;
	private int scoreMark;
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public int getScoreMark() {
		return scoreMark;
	}
	public void setScoreMark(int scoreMark) {
		this.scoreMark = scoreMark;
	}

	
}
