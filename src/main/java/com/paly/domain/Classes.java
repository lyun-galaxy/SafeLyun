package com.paly.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 班级
 * @author linyu
 *
 */
public class Classes implements Serializable {

	private static final long serialVersionUID = 4158176263435498562L;

    private Integer classesId;

    /**
     * 班级名
     */
    private String classesName;

    /**
     * 班级属于的专业
     */
    private Specialty specialty;

    /**
     * 班级下的学生列表
     */
    private List<Student> students;
    
    
    public Classes() {
		super();
	}

    /**
     * 通过传递参数实例化班级
     * @param classesName 班级名，不能为null
     * @param specialty 所属的专业{@link Specialty} 不能为null
     */
	public Classes(String classesName, Specialty specialty) {
		super();
		this.classesName = classesName;
		this.specialty = specialty;
	}


	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column classes.classesId
     *
     * @return the value of classes.classesId
     *
     * @mbggenerated
     */
    public Integer getClassesId() {
        return classesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column classes.classesId
     *
     * @param classesId the value for classes.classesId
     *
     * @mbggenerated
     */
    public void setClassesId(Integer classesId) {
        this.classesId = classesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column classes.classesName
     *
     * @return the value of classes.classesName
     *
     * @mbggenerated
     */
    public String getClassesName() {
        return classesName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column classes.classesName
     *
     * @param classesName the value for classes.classesName
     *
     * @mbggenerated
     */
    public void setClassesName(String classesName) {
        this.classesName = classesName;
    }

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}	

}