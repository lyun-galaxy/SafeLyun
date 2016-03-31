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
     * 获取角色下的所有用户列表
     * @param roleId 角色Id
     * @return 返回角色下的所有用户列表
     */
    List<User> getUsersByRoleId(int roleId);
}