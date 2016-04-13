package com.paly.mapper;
import java.util.List;

import com.paly.domain.Subsection;

/**
 * 子章节Dao接口
 * @author linyu
 *
 */
public interface SubsectionMapper extends BaseMapper<Subsection> {
   
	/**
	 * 根据章节Id获取其下的子章节列表
	 * @param sectionId 章节id
	 * @return 返回指定章节下的子章节列表
	 */
	List<Subsection> getBySectionId(int sectionId);
	
	/**
	 * 根据学习进度id获取其关联的子章节
	 * @param studyscheduleId 学习进度id
	 * @return 返回指定学习进度的子章节信息
	 */
	Subsection getByStudyscheduleId(int studyscheduleId);
	
	/**
	 * 查询经过审核的子章节
	 * @return 返回已审核的子章节列表
	 */
	List<Subsection> queryIsChecked();

	/**
	 * 查询未经过审核的子章节
	 * @return 返回未审核的子章节列表
	 */
	List<Subsection> queryIsNotChecked();
}
