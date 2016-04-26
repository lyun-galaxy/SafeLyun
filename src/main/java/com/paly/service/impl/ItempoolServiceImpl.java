package com.paly.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class ItempoolServiceImpl extends BaseServiceImpl<Itempool> implements ItempoolService {
	@Resource
	private ItempoolMapper itempoolMapper;

	@Override
	public BaseMapper<Itempool> getBaseMapper() {
		return itempoolMapper;
	}

	@Override
	public List<Itempool> randomCreateChoiceExams(int count) {
		List<Itempool> itempool = itempoolMapper.selectAll();
		List<Itempool> result = new ArrayList<Itempool>();
		// 打乱集合
		Collections.shuffle(itempool);
		for (int i = 0; i < count && i < itempool.size(); i++) {
			result.add(itempool.get(i));
		}
		return result;
	}

}
