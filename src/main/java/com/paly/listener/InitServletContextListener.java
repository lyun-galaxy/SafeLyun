package com.paly.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.paly.domain.Menu;
import com.paly.service.MenuService;

/**
 * 在容器加载时从数据库中查找菜单信息
 * @author root
 *
 */
public class InitServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();

		// 得到Service的实例对象
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
		MenuService menuService = (MenuService) ac.getBean("menuService");

		// 准备所有顶级权限的集合（顶级菜单）
		List<Menu> topPrivilegeList = menuService.getTop();
		application.setAttribute("topMenuList", topPrivilegeList);
		System.out.println("-- 已准备好顶级权限的数据 --");

		// 准备所有权限URL的集合(菜单URL)
		List<String> allMenuUrls = menuService.getMenuUrls();
		application.setAttribute("allMenuUrls", allMenuUrls);
		System.out.println("-- 已准备好所有权限的URL数据 --");
	}

}
