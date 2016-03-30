package com.paly.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class BaseController {
	
	//将json返回页面
	public void writeJson(Object object,HttpServletResponse response) {	
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		   } catch (IOException e) {
			e.printStackTrace();
		}
	}
}
