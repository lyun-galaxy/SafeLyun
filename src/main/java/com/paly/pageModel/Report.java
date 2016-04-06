package com.paly.pageModel;

public class Report extends BaseModel {

	private String gradeId;
	private String grade;
	private String departId;
	private String departName;
	private String proId;
	private String proName;
	private String classId;
	private String className;
	private double completeRate;
	private double passRate;

	public Report() {
		super();
	}

	public Report(String grade, String departId, String departName, String proId, String proName, String classId,
			String className, double completeRate, double passRate) {
		super();
		this.grade = grade;
		this.departId = departId;
		this.departName = departName;
		this.proId = proId;
		this.proName = proName;
		this.classId = classId;
		this.className = className;
		this.completeRate = completeRate;
		this.passRate = passRate;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public double getCompleteRate() {
		return completeRate;
	}

	public void setCompleteRate(double completeRate) {
		this.completeRate = completeRate;
	}

	public double getPassRate() {
		return passRate;
	}

	public void setPassRate(double passRate) {
		this.passRate = passRate;
	}

}
