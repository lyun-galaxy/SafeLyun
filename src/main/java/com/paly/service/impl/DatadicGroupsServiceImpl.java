package com.paly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paly.domain.DatadicGroups;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.DatadicGroupsMapper;
import com.paly.service.DatadicGroupsService;

/**
 * 数据字典
 * 
 * @author luohuaming
 *
 */
@Service("datadicGroupsService")
public class DatadicGroupsServiceImpl extends BaseServiceImpl<DatadicGroups> implements DatadicGroupsService {

	@Autowired
	private DatadicGroupsMapper datadicGroupsMapper;

	@Override
	public DatadicGroups getByItemCode(String itemCode) {
		return datadicGroupsMapper.getByItemCode(itemCode);
	}

	@Override
	public void batchAddItemsOfGroup(DatadicGroups itemsOfGroup) {
		datadicGroupsMapper.batchAddItemsOfGroup(itemsOfGroup);
	}

	@Override
	public BaseMapper<DatadicGroups> getBaseMapper() {
		return datadicGroupsMapper;
	}

}
