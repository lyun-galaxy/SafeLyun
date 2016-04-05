package com.paly.mapper;

import java.util.List;

import com.paly.domain.DatadicItems;

/**
 * 数据字典项Dao接口
 * @author linyu
 *
 */
public interface DatadicItemsMapper extends BaseMapper<DatadicItems> {
 
	/**
	 * 根据数据字典分组编码获取其关联的数据字典项列表
	 * @param groupCode 数据字典分组编码
	 * @return 返回指定数据字典分组下的数据字典项列表
	 */
	List<DatadicItems> getByGroupCode(String groupCode);
}