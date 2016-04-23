package com.paly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paly.domain.Role;

/**
 * 角色Dao接口
 * @author linyu
 *
 */
public interface RoleMapper extends BaseMapper<Role> {   
    
    /**
     * 获取用户拥有的角色列表
     * @param userId 用户id
     * @return 返回用户拥有的角色列表
     */
    List<Role> getByUserId(int userId);
    
    /**
     * 设置角色拥有哪个菜单
     * @param roleId 角色id
     * @param menuId 将要拥有的菜单id
     */
    void setRoleHasMenu(@Param("roleId")int roleId, @Param("menuId")int menuId);
    
    /**
     * 获取菜单属于哪些角色
     * @param menuId 菜单id
     * @return	返回菜单属于哪些角色的角色列表
     */
    List<Role> getByMenuId(int menuId);
}