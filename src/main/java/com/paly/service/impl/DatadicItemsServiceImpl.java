package com.paly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paly.domain.DatadicItems;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.DatadicItemsMapper;
import com.paly.service.DatadicItemsService;

/**
 * 数据字典项Service实现
 * 
 * @author luohuaming
 *
 */
@Service("datadicItemsService")
public class DatadicItemsServiceImpl extends BaseServiceImpl<DatadicItems> implements DatadicItemsService {
	@Autowired
	private DatadicItemsMapper datadicItemsMapper;

	@Override
	public List<DatadicItems> getByGroupCode(String groupCode) {
		return datadicItemsMapper.getByGroupCode(groupCode);
	}

	@Override
	public BaseMapper<DatadicItems> getBaseMapper() {
		return datadicItemsMapper;
	}

}
