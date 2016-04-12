package com.paly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paly.domain.User;

/**
 * 用户Dao接口
 * @author linyu
 *
 */
public interface UserMapper extends BaseMapper<User> {
    
    /**
     * 设置用户拥有哪个角色
     * @param userId 用户Id
     * @param roleId 将要拥有的角色Id
     */
    void setUserHasRole(@Param("userId")int userId, @Param("roleId")int roleId);
    
    /**
     * 设置用户管理的班级
     * @param userId 用户id
     * @param classesId 将要管理的班级id
     */
    void setUserHasClasses(@Param("userId")int userId, @Param("classesId")int classesId);
    
    /**
     * 获取角色下的所有用户列表
     * @param roleId 角色Id
     * @return 返回角色下的所有用户列表
     */
    List<User> getByRoleId(int roleId);
    
    /**
     * 根据学生id获取与其关联的用户
     * @param studentId 学生id
     * @return 返回与指定学生关联的用户
     */
    User getByStudentId(int studentId);
    
    
}