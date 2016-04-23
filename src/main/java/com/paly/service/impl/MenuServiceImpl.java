package com.paly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Menu;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.MenuMapper;
import com.paly.service.MenuService;

/**
 * 菜单Service接口实现
 * 
 * @author root
 *
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {
	@Resource
	private MenuMapper menuMapper;

	@Override
	public List<Menu> getByRoleId(int roleId) {
		return menuMapper.getByRoleId(roleId);
	}

	@Override
	public BaseMapper<Menu> getBaseMapper() {
		return menuMapper;
	}

	@Override
	public List<Menu> getTop() {
		//TODO 获取所有顶级菜单
		return null;
	}

	@Override
	public List<String> getMenuUrls() {
		List<Menu> list = menuMapper.selectAll();
		List<String> urls = new ArrayList<String>();
		for(Menu menu : list){
			urls.add(menu.getMenuUrl());
		}
		return urls;
	}
	

}
