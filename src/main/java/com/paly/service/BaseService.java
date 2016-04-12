package com.paly.service;

import java.io.Serializable;
import java.util.List;

import com.paly.domain.Page;

/**
 * 通用Service接口
 * @author root
 *
 * @param <T>
 */
public interface BaseService<T extends Serializable> {
	/**
	 * 添加
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 *修改
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 *根据id查询
	 * 
	 * @param id
	 * @return
	 */
	T getById(Integer id);

	/**
	 *根据id数组查找
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Integer[] ids);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<T> findAll();
	/**
	 * 分页查询
	 * @param pagenum 页号
	 * @return
	 */
	Page<T> findByPage(String pagenum);
}
