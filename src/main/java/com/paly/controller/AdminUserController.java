package com.paly.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.paly.service.AdminUserService;
import com.paly.vo.Json;
/**
 * 
 * @author Royal
 *
 */
@Controller
public class AdminUserController extends AdminBaseController{
	private static final Logger logger = LoggerFactory.getLogger(AdminQuestionBankController.class);
	
	@Resource
	AdminUserService adminUserService;
	
	@RequestMapping("/adminuser/importfile.action")
	public void importfile(MultipartFile file,HttpServletResponse response,HttpServletRequest request){
		
        Json json = new Json();     
        CommonsMultipartFile cf= (CommonsMultipartFile)file; 
        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        File f = fi.getStoreLocation(); 
        
		try {
			String path = super.saveUploadFile(request, f, "EMPFILE", "xls");
			logger.info(path);
			adminUserService.importStudentBaseMSG(path);
			json.setSuccess(true);
			json.setMsg("导入成功！");
		} catch (Exception e) {
			json.setMsg(e.toString());
		}
		
	    super.writeJson(json,response);
	}
}
