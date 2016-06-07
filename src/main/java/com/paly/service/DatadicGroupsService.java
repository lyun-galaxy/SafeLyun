package com.paly.service;

import com.paly.domain.DatadicGroups;

/**
 * 数据字典分组Service接口
 * 
 * @author luohuaming
 *
 */
public interface DatadicGroupsService extends BaseService<DatadicGroups> {
	/**
	 * 根据数据字典项编码查找与其关联的分组信息
	 * 
	 * @param itemCode
	 *            数据字典项编码
	 * @return 返回指定数据字典项的分组信息
	 */
	DatadicGroups getByItemCode(String itemCode);

	/**
	 * 批量添加数据字典分组下的字典项<br>
	 * 由于外键约束，DatadicGroups必须从数据库中取，而不能自己创建
	 * 
	 * @param itemsOfGroup
	 *            数据字典分组必须从数据库中取
	 * 
	 */
	void batchAddItemsOfGroup(DatadicGroups itemsOfGroup);
	
	/**
	 * 通过数据字典项分组编码获取
	 * @param groupCode 数据字典项分组编码
	 * @return 数据字典项分组
	 */
	DatadicGroups selectByPrimaryKey(String groupCode);
}
