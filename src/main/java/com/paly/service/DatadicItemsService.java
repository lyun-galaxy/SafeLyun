package com.paly.service;

import java.util.List;

import com.paly.domain.DatadicItems;

/**
 * 数据字典项Service接口
 * 
 * @author luohuaming
 *
 */
public interface DatadicItemsService extends BaseService<DatadicItems> {
	/**
	 * 根据数据字典分组编码获取其关联的数据字典项列表
	 * 
	 * @param groupCode
	 *            数据字典分组编码
	 * @return 返回指定数据字典分组下的数据字典项列表
	 */
	List<DatadicItems> getByGroupCode(String groupCode);
}
