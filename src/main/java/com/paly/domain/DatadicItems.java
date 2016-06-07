package com.paly.domain;

import java.io.Serializable;

/**
 * 数据字典项
 * 
 * @author linyu
 *
 */
public class DatadicItems implements Serializable {

	private static final long serialVersionUID = 2498575044111939329L;

	/**
	 * 数据字典项编码
	 */
	private String itemCode;

	/**
	 * 数据字典项名称
	 */
	private String itemName;

	/**
	 * 与其管理的数据字典分组
	 */
	private DatadicGroups datadicGroup;

	public DatadicItems() {
		super();
	}

	/**
	 * 实例化数据字典项并指定其所属的分组
	 * 
	 * @param itemCode
	 *            数据字典项编码
	 * @param itemName
	 *            数据字典项名
	 * @param datadicGroup
	 *            数据字典项所属的分组
	 */
	public DatadicItems(String itemCode, String itemName, DatadicGroups datadicGroup) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.datadicGroup = datadicGroup;
	}

	public String getItemCode() {
		return itemCode;
	}

	public String getItem_code() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItem_name() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public DatadicGroups getDatadicGroup() {
		return datadicGroup;
	}

	public void setDatadicGroup(DatadicGroups datadicGroup) {
		this.datadicGroup = datadicGroup;
	}
}