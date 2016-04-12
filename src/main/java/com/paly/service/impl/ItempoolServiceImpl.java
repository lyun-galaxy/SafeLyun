package com.paly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Itempool;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.ItempoolMapper;
import com.paly.service.ItempoolService;

/**
 * 题库Service接口实现
 * 
 * @author root
 *
 */
@Service("itempoolService")
public class ItempoolServiceImpl extends BaseServiceImpl<Itempool> implements ItempoolService {
	@Resource
	private ItempoolMapper itempoolMapper;

	@Override
	public BaseMapper<Itempool> getBaseMapper() {
		return itempoolMapper;
	}

}
