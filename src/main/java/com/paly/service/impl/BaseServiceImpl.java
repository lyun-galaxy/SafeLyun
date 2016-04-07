package com.paly.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.paly.domain.Page;
import com.paly.mapper.BaseMapper;
import com.paly.service.BaseService;

/**
 * 通用Service实现类
 * 
 * @author root
 *
 * @param <T>
 */
public abstract class BaseServiceImpl<T extends Serializable> implements BaseService<T> {
	/**
	 * 子类提供Mapper类
	 * 
	 * @return
	 */
	public abstract BaseMapper<T> getBaseMapper();

	@Override
	public void save(T entity) {
		getBaseMapper().insert(entity);
	}

	@Override
	public void delete(Integer id) {
		getBaseMapper().deleteByPrimaryKey(id);
	}

	@Override
	public void update(T entity) {
		getBaseMapper().updateByPrimaryKey(entity);
	}

	@Override
	public T getById(Integer id) {
		return getBaseMapper().selectByPrimaryKey(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByIds(Integer[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}
		return null;
	}

	@Override
	public List<T> findAll() {
		return getBaseMapper().selectAll();
	}

	public long getTotalRecord() {
		return 0;
	}

	/**
	 * 从数据库中获取分页信息
	 * 
	 * @param startindex
	 *            页面起始索引
	 * @param pagesize
	 *            页面大小
	 * @return
	 */
	private List<T> getByPage(int startindex, int pagesize) {
		/*
		 * return getCurrentSession() .createQuery( "FROM " +
		 * getClazz().getSimpleName() + " ORDER BY id desc")
		 * .setFirstResult(startindex).setMaxResults(pagesize).list();
		 */
		return null;
	}

	@Override
	public Page<T> findByPage(String pagenum) {
		long totalrecord = getTotalRecord();
		Page<T> page = null;
		if (pagenum == null) {
			page = new Page<T>(1, 0);
		} else {
			page = new Page<T>(Integer.parseInt(pagenum), totalrecord);
		}
		List<T> list = getByPage(page.getStartindex(), page.getPagesize());
		page.setList(list);
		return page;
	}

}
