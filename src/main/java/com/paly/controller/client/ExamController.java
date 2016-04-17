package com.paly.controller.client;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.paly.controller.BaseController;
import com.paly.domain.Itempool;
import com.paly.service.ItempoolService;
import com.paly.vo.ItempoolCustom;

/**
 * 在线考试Controller
 * 
 * @author luohuaming
 *
 */
@Controller
@RequestMapping("/client_exam")
public class ExamController extends BaseController {
	@Resource
	private ItempoolService itempoolService;

	/**
	 * 从题库中随机获取试题，发送试题列表json数据
	 * 
	 * @param response
	 *            请求头
	 */
	@RequestMapping("/getEpaper")
	public void getEpaper(HttpServletResponse response, HttpSession session) {
		List<Itempool> choiceList = itempoolService.randomCreateChoiceExams(10);
		// 将考题存入session域中
		session.setAttribute("correctexams", choiceList);
		writeJson(choiceList, response);
	}

	/**
	 * 校验答案
	 * 
	 * @return
	 */
	@RequestMapping("/submitTestPaper")
	public ModelAndView submitTestPaper(ItempoolCustom itempoolCustom, HttpSession session) {
		// 统计分数
		int fration = statistics(itempoolCustom.getChoiceList(), session);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("submitTestPaperok", fration + "分");
		modelAndView.setViewName("../../score");
		return modelAndView;
	}

	/**
	 * 统计分数
	 * 
	 * @param myChoices
	 *            考生的答案
	 * @param session
	 *            需从session域中获取正确答案
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int statistics(List<Itempool> myChoices, HttpSession session) {
		int fration = 0;
		// 从session域中获取正确考题
		List<Itempool> correctexams = (List<Itempool>) session.getAttribute("correctexams");
		for (int i = 0; i < correctexams.size(); i++) {
			Itempool correct = correctexams.get(i);
			Itempool myChoice = myChoices.get(i);
			if (correct.getItempoolCorrect().equalsIgnoreCase(myChoice.getItempoolCorrect())) {
				// TODO 每题的分数
				fration += 10;
			}
		}
		return fration;
	}
}
