package com.paly.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
/**
 * 
 * @author Royal
 *
 */
public class BaseController {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);
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
	
	protected String saveUploadFile(HttpServletRequest request,File upload, String uploadpath,String type) {
		if (upload == null)
			return "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("\\yyyy\\MM\\dd\\");
		String basePath = request.getSession().getServletContext().getRealPath(uploadpath);
		logger.info("==============" + basePath);
		String subPath = sdf.format(new Date());
		File dir = new File(basePath + subPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String path = basePath + subPath + UUID.randomUUID().toString()+"."+type;

		upload.renameTo(new File(path));
		return path;
	}
}
