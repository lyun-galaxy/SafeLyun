package com.paly.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 数据字典项分组
 * @author linyu
 *
 */
public class DatadicGroups implements Serializable {
	private static final long serialVersionUID = 5679091641009243751L;

	/**
	 * 数据字典项分组编码
	 */
    private String groupCode;

    /**
     * 数据字典项分组名称
     */
    private String groupName;

    /**
     * 数据字典项列表
     */
    private List<DatadicItems> items;
    
    
    public DatadicGroups() {
		super();
	}

    
	public DatadicGroups(String groupCode, String groupName, List<DatadicItems> items) {
		super();
		this.groupCode = groupCode;
		this.groupName = groupName;
		this.items = items;
	}


	public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

	public List<DatadicItems> getItems() {
		return items;
	}

	public void setItems(List<DatadicItems> items) {
		this.items = items;
	}
}