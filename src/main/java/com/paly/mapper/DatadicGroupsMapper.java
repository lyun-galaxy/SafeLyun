package com.paly.mapper;

import com.paly.domain.DatadicGroups;

/**
 * 数据字典分组Dao接口
 * @author linyu
 *
 */
public interface DatadicGroupsMapper extends BaseMapper<DatadicGroups> {
    
	/**
	 * 根据数据字典项编码查找与其关联的分组信息
	 * @param itemCode 数据字典项编码
	 * @return 返回指定数据字典项的分组信息
	 */
	DatadicGroups getByItemCode(String itemCode);
	
	/**
	 * 批量添加数据字典分组下的字典项<br>
	 * 由于外键约束，DatadicGroups必须从数据库中取，而不能自己创建
	 * 
	 * @param itemsOfGroup 数据字典分组必须从数据库中取
	 * @exception org.apache.ibatis.exceptions.PersistenceException
	 */
	void batchAddItemsOfGroup(DatadicGroups itemsOfGroup);
}