package com.paly.domain;

import java.io.Serializable;

/**
 * 考试开关
 * @author linyu
 *
 */
public class Examswitch implements Serializable {
    
	private static final long serialVersionUID = 5340756347843962311L;

	/**
	 * 考试开关主键,固定值1
	 */
    private Integer examswitchId;

    /**
     * 是否开启考试,默认为false关闭状态
     */
    private Boolean switchOnOrOff;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column examswitch.examswitchId
     *
     * @return the value of examswitch.examswitchId
     *
     * @mbggenerated
     */
    public Integer getExamswitchId() {
        return examswitchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column examswitch.examswitchId
     *
     * @param examswitchId the value for examswitch.examswitchId
     *
     * @mbggenerated
     */
    public void setExamswitchId(Integer examswitchId) {
        this.examswitchId = examswitchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column examswitch.switchOnOrOff
     *
     * @return the value of examswitch.switchOnOrOff
     *
     * @mbggenerated
     */
    public Boolean getSwitchOnOrOff() {
        return switchOnOrOff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column examswitch.switchOnOrOff
     *
     * @param switchOnOrOff the value for examswitch.switchOnOrOff
     *
     * @mbggenerated
     */
    public void setSwitchOnOrOff(Boolean switchOnOrOff) {
        this.switchOnOrOff = switchOnOrOff;
    }
}