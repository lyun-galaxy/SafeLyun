package com.paly.mapper;

import java.io.Serializable;
import java.util.List;	

/**
 * 通用mapper接口,包含基本的CRUD
 * @author linyu
 *
 * @param <T> 实体类型,必须实现Serializable接口
 */
public interface BaseMapper<T extends Serializable> {
	
	/**
	 * 添加一条记录
	 * @param record 
	 * @return 返回插入的条数
	 */
	int insert(T record);
	/**
	 * 根据id删除一条记录
	 * @param id 记录的id
	 * @return 返回删除的条数
	 */
	int deleteByPrimaryKey(Integer id);
	/**
	 * 更新记录
	 * @param record 要被更新的记录
	 * @return 返回更新的条数
	 */
	int updateByPrimaryKey(T record);
	/**
	 * 根据id查询记录
	 * @param id 记录的Id
	 * @return 返回查询的记录
	 */
	T selectByPrimaryKey(Integer id);
	/**
	 * 查询所有记录
	 * @return 返回所有记录列表
	 */
	List<T> selectAll();
}
