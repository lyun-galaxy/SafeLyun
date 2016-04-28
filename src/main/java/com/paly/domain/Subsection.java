package com.paly.domain;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 子章节
 * @author linyu
 *
 */
public class Subsection implements Serializable{
   
	private static final long serialVersionUID = 824814283958151371L;

	
    private Integer subsectionId;

    /**
     * 子章节名
     */
    private String subsectionName;

    /**
     * 存储子章节时长
     */
    private int subsectionTime;

	/**
	 * 子章节内容
     */
    private String subsectionContent;
    
    /**
     * 子章节编号
     */
    private int subsectionCode;
    
    /**
     * 该子章节是否被审核
     */
    private Boolean subsectionChecked;

    /**
     * 子章节上传者
     */
    private String subsectionUploader;
    
    /**
     * 子章节所属的章节
     */
  //解决延迟加载导致转换成json对象异常
    @JSONField(serialize=false)
    private Section section;
    

    public Subsection() {
		super();
	}

    /**
     * 带参实例化子章节实体
     * @param subsectionName 子章节名称
     * @param subsectionTime 子章节时间
     * @param subsectionContent 子章节内容
     * @param subsectionChecked 子章节是否被审核
     * @param subsectionCode 子章节编号
     * @param subsectionCode 子章节上传者，不能为空
     * @param section 所属的章节
     */
    public Subsection(String subsectionName, int subsectionTime, String subsectionContent, Boolean subsectionChecked,
			int subsectionCode, String subsectionUploader, Section section) {
		super();
		this.subsectionName = subsectionName;
		this.subsectionTime = subsectionTime;
		this.subsectionContent = subsectionContent;
		this.subsectionChecked = subsectionChecked;
		this.subsectionCode = subsectionCode;
		this.subsectionUploader = subsectionUploader;
		this.section = section;
	}





	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subsection.subsectionId
     *
     * @return the value of subsection.subsectionId
     *
     * @mbggenerated
     */
    public Integer getSubsectionId() {
        return subsectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subsection.subsectionId
     *
     * @param subsectionId the value for subsection.subsectionId
     *
     * @mbggenerated
     */
    public void setSubsectionId(Integer subsectionId) {
        this.subsectionId = subsectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subsection.subsectionName
     *
     * @return the value of subsection.subsectionName
     *
     * @mbggenerated
     */
    public String getSubsectionName() {
        return subsectionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subsection.subsectionName
     *
     * @param subsectionName the value for subsection.subsectionName
     *
     * @mbggenerated
     */
    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

   
    public int getSubsectionTime() {
        return subsectionTime;
    }

    public void setSubsectionTime(int subsectionTime) {
        this.subsectionTime = subsectionTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subsection.subsectionChecked
     *
     * @return the value of subsection.subsectionChecked
     *
     * @mbggenerated
     */
    public Boolean getSubsectionChecked() {
        return subsectionChecked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subsection.subsectionChecked
     *
     * @param subsectionChecked the value for subsection.subsectionChecked
     *
     * @mbggenerated
     */
    public void setSubsectionChecked(Boolean subsectionChecked) {
        this.subsectionChecked = subsectionChecked;
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subsection.subsectionContent
     *
     * @return the value of subsection.subsectionContent
     *
     * @mbggenerated
     */
    public String getSubsectionContent() {
        return subsectionContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subsection.subsectionContent
     *
     * @param subsectionContent the value for subsection.subsectionContent
     *
     * @mbggenerated
     */
    public void setSubsectionContent(String subsectionContent) {
        this.subsectionContent = subsectionContent;
    }

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public int getSubsectionCode() {
		return subsectionCode;
	}

	public void setSubsectionCode(int subsectionCode) {
		this.subsectionCode = subsectionCode;
	}

	public String getSubsectionUploader() {
		return subsectionUploader;
	}

	public void setSubsectionUploader(String subsectionUploader) {
		this.subsectionUploader = subsectionUploader;
	}
}
