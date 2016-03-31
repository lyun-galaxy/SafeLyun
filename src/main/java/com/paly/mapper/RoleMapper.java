package com.paly.mapper;

import com.paly.domain.Role;
import java.util.List;

/**
 * 角色Dao接口
 * @author linyu
 *
 */
public interface RoleMapper extends BaseMapper<Role> {   
    
    /**
     * 查询用户拥有的角色列表
     * @param userId 用户id
     * @return 返回用户拥有的角色列表
     */
    List<Role> getRolesByUserId(int userId);
}