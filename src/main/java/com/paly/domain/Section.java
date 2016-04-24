package com.paly.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 章节
 * @author linyu
 *
 */
public class Section implements Serializable {
    
	private static final long serialVersionUID = -5897895452712431531L;
	
    private Integer sectionId;

    /**
     * 章节名
     */
    private String sectionName;

    /**
     * 该章节是否被审核
     */
    private Boolean sectionChecked;
        
    /**
     * 章节编号
     */
    private int sectionCode;
    
    /**
     * 与子章节关联，拥有多个子章节
     */
  //解决延迟加载导致转换成json对象异常
   // @JSONField(serialize=false)
    private List<Subsection> subsections;

    
    public Section() {
		super();
	}

	public Section(String sectionName, Boolean sectionChecked, int sectionCode) {
		super();
		this.sectionName = sectionName;
		this.sectionChecked = sectionChecked;
		this.sectionCode = sectionCode;
	}

	public List<Subsection> getSubsections() {
		return subsections;
	}

	public void setSubsections(List<Subsection> subsections) {
		this.subsections = subsections;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column section.sectionId
     *
     * @return the value of section.sectionId
     *
     * @mbggenerated
     */
    public Integer getSectionId() {
        return sectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column section.sectionId
     *
     * @param sectionId the value for section.sectionId
     *
     * @mbggenerated
     */
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column section.sectionName
     *
     * @return the value of section.sectionName
     *
     * @mbggenerated
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column section.sectionName
     *
     * @param sectionName the value for section.sectionName
     *
     * @mbggenerated
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column section.sectionChecked
     *
     * @return the value of section.sectionChecked
     *
     * @mbggenerated
     */
    public Boolean getSectionChecked() {
        return sectionChecked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column section.sectionChecked
     *
     * @param sectionChecked the value for section.sectionChecked
     *
     * @mbggenerated
     */
    public void setSectionChecked(Boolean sectionChecked) {
        this.sectionChecked = sectionChecked;
    }

	public int getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(int sectionCode) {
		this.sectionCode = sectionCode;
	}
}
