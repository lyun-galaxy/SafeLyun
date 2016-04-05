package com.paly.domain;

import java.io.Serializable;

/**
 * 学习进度
 * @author linyu
 *
 */
public class Studyschedule implements Serializable {

	private static final long serialVersionUID = 5548996017696729231L;

	private Integer studyscheduleId;

    /**
     * 已学习子章节数目
     */
    private Integer studyscheduleHasNum;

    /**
     * 与其关联的子章节
     */
    private Subsection subsection;

    /**
     * 与其关联的学生
     */
    private Student student;
    
    public Studyschedule() {
		super();
	}

	public Integer getStudyscheduleId() {
        return studyscheduleId;
    }

    public void setStudyscheduleId(Integer studyscheduleId) {
        this.studyscheduleId = studyscheduleId;
    }

    public Integer getStudyscheduleHasNum() {
        return studyscheduleHasNum;
    }

    public void setStudyscheduleHasNum(Integer studyscheduleHasNum) {
        this.studyscheduleHasNum = studyscheduleHasNum;
    }

    public Subsection getSubsection() {
        return subsection;
    }

    public void setSubsection(Subsection subsection) {
        this.subsection = subsection;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}