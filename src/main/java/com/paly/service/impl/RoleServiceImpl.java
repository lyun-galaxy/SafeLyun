package com.paly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Role;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.RoleMapper;
import com.paly.service.RoleService;

/**
 * 角色Service接口实现
 * @author luohuaming
 *
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	@Resource
	private RoleMapper roleMapper;

	@Override
	public List<Role> getByUserId(int userId) {
		return roleMapper.getByUserId(userId);
	}

	@Override
	public void setRoleHasMenu(int roleId, int menuId) {
		roleMapper.setRoleHasMenu(roleId, menuId);
	}

	@Override
	public List<Role> getByMenuId(int menuId) {
		return roleMapper.getByMenuId(menuId);
	}

	@Override
	public BaseMapper<Role> getBaseMapper() {
		return roleMapper;
	}

}
