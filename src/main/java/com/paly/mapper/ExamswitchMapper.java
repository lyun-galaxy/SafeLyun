package com.paly.mapper;

import com.paly.domain.Examswitch;

/**
 * 考试开关Dao接口<br>
 * <b>注意:</b><br>
 * {@link BaseMapper#insert(java.io.Serializable)}语句只允许执行一次,且通过
 * {@link Examswitch#setSwitchOnOrOff(Boolean)}方法设置是否开启考试。<br>
 * 以后通过{@link BaseMapper#selectByPrimaryKey(Integer)}方法查看状态，并通过
 * {@link BaseMapper#updateByPrimaryKey(java.io.Serializable)}方法更新状态。
 * <br>另，若数据库有超过一条记录则必须删除至只有一条，以便保持数据库只有一条记录。可通过
 * {@link BaseMapper#selectAll()}查询所有记录。
 * @author linyu
 *
 */
public interface ExamswitchMapper extends BaseMapper<Examswitch> {
    
}