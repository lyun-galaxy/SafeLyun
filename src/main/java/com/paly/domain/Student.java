package com.paly.domain;

import java.io.Serializable;

/**
 * 学生
 * @author linyu
 *
 */
public class Student implements Serializable {
    
	private static final long serialVersionUID = 8991811301801914147L;

    private Integer studentId;

    /**
     * 学号
     */
    private String studentNumber;

    /**
     * 姓名
     */
    private String studentName;

    /**
     * 年级，格式：2013
     */
    private String grade;
    /**
     * 性别
     */
    private String studentSex;

    /**
     * 邮箱
     */
    private String studentEmail;
    
    /**
     * 与用户关联,学生属于用户
     */
    private User user;

    /**
     * 与班级关联，学生属于班级
     */
    private Classes classes;

    /**
     * 与成绩关联，学生拥有成绩
     */
    private Score score;
    
    public Student() {
		super();
	}


    /**
     * 实例化学生实体
     * @param studentNumber 学号，不为空
     * @param studentName 姓名，不为空
     * @param grade 年级，不为空
     * @param studentSex 性别
     * @param studentEmail 邮箱
     * @param user 所属的用户，不为空
     * @param classes 所属的班级，不为空
     * @param score 拥有的成绩，不为空
     */
	public Student(String studentNumber, String studentName, String grade, String studentSex, String studentEmail,
			User user, Classes classes, Score score) {
		super();
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.grade = grade;
		this.studentSex = studentSex;
		this.studentEmail = studentEmail;
		this.user = user;
		this.classes = classes;
		this.score = score;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.studentId
     *
     * @return the value of student.studentId
     *
     * @mbggenerated
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.studentId
     *
     * @param studentId the value for student.studentId
     *
     * @mbggenerated
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.studentNumber
     *
     * @return the value of student.studentNumber
     *
     * @mbggenerated
     */
    public String getStudentNumber() {
        return studentNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.studentNumber
     *
     * @param studentNumber the value for student.studentNumber
     *
     * @mbggenerated
     */
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.studentName
     *
     * @return the value of student.studentName
     *
     * @mbggenerated
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.studentName
     *
     * @param studentName the value for student.studentName
     *
     * @mbggenerated
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.studentSex
     *
     * @return the value of student.studentSex
     *
     * @mbggenerated
     */
    public String getStudentSex() {
        return studentSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.studentSex
     *
     * @param studentSex the value for student.studentSex
     *
     * @mbggenerated
     */
    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.studentEmail
     *
     * @return the value of student.studentEmail
     *
     * @mbggenerated
     */
    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.studentEmail
     *
     * @param studentEmail the value for student.studentEmail
     *
     * @mbggenerated
     */
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}


	public Score getScore() {
		return score;
	}


	public void setScore(Score score) {
		this.score = score;
	}





	public String getGrade() {
		return grade;
	}





	public void setGrade(String grade) {
		this.grade = grade;
	}

    
}