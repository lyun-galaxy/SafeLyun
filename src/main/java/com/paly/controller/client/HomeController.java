
package com.paly.controller.client;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.paly.domain.Examswitch;
import com.paly.service.ExamswitchService;
import com.paly.service.StudyscheduleService;
import com.paly.service.UserService;

/**
 * 客户端主界面Controller
 * 
 * @author luohuaming
 *
 */
@Controller
@RequestMapping("/client_home")
public class HomeController {
	@Resource
	private UserService userService;
	@Resource
	private ExamswitchService examswitchService;
	@Resource
	private StudyscheduleService studyscheduleService;

	/**
	 * 跳转到主界面
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/toHomePage")
	public ModelAndView toHomePage(HttpSession session) {
		// 获取考试开关
		Examswitch examswitch = examswitchService.getExamswitch();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("examswitch", examswitch);
//		studyscheduleService.
		modelAndView.setViewName("/home");
		return modelAndView;
	}
}
