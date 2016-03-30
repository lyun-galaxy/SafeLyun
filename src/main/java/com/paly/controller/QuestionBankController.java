package com.paly.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paly.domain.Json;
import com.paly.domain.Tquestion;


@Controller
public class QuestionBankController extends BaseController{

	private static final Logger logger = Logger.getLogger(QuestionBankController.class);

	@RequestMapping("/add.action")
	public void add(Tquestion tq,HttpServletResponse response){
		
		Json json = new Json();
		try {
			logger.info("add");
			json.setSuccess(true);
			json.setMsg("添加成功！");
			json.setObj(tq);

		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}

		super.writeJson(json, response);
		System.out.println("add");
	}
	
	
}
