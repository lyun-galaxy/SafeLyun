package com.paly.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paly.domain.Json;
import com.paly.domain.Tquestion;


@Controller
public class QuestionBankController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(QuestionBankController.class);

	//添加题目
	@RequestMapping("/add.action")
	public void add(Tquestion tq,HttpServletResponse response){
		
		Json json = new Json();
		try {
			logger.info("add");
			//预留保存题目到数据库
			json.setSuccess(true);
			json.setMsg("添加成功！");
			json.setObj(tq);

		} catch (Exception e) {
			json.setMsg(e.getMessage());  //将保存的数据返回页面进行回显
		}

		super.writeJson(json, response);
		System.out.println("add");
	}
	
	
	
	
}
