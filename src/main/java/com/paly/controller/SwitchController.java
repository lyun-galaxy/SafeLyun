package com.paly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paly.service.AdminExamSwichService;
import com.paly.vo.Json;

@Controller
public class SwitchController extends BaseController{

	@Resource
	AdminExamSwichService adminExamSwichService;
	
	@RequestMapping("/switchController/changeFlag.action")
	public void changeFlag(HttpServletResponse response){
		Json json = new Json();
		try {
			Boolean flag = adminExamSwichService.getFlag();
			adminExamSwichService.changFlag(flag);
			json.setSuccess(true);
			if(flag){
			    json.setMsg("关闭考试开关！");
			}else{
				json.setMsg("开启考试开关！");
			}
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		super.writeJson(json,response);
	}
	
	@RequestMapping("/switchController/getFlag.action")
	public void getFlag(HttpServletResponse response){
		Json json = new Json();
		try {
			//adminExamSwichService.changFlag();
			Boolean flag = adminExamSwichService.getFlag();	
	        json.setFlag(flag);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		super.writeJson(json,response);
	}
	
}
