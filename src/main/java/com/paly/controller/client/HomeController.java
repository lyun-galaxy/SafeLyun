
package com.paly.controller.client;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.paly.domain.Examswitch;
import com.paly.domain.Json;
import com.paly.domain.Student;
import com.paly.domain.Studyschedule;
import com.paly.domain.User;
import com.paly.service.ExamswitchService;
import com.paly.service.StudentService;
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
	@Resource
	private StudentService studentService;

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
		// 获取用户学习进度
		Studyschedule studyschedule = getUserStudyschedule(session);
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) session.getAttribute("user");
		Student student = studentService.selectByStudentNumber(user.getUserName());
		// 添加考试开关信息到视图
		modelAndView.addObject("examswitch", examswitch);
		// 添加用户学习进度
		modelAndView.addObject("studyschedule", studyschedule);
		modelAndView.addObject("student",student);
		// studyscheduleService.
		modelAndView.setViewName("client/main.jsp");
		return modelAndView;
	}

	/**
	 * 获取用户当前学习进度
	 * 
	 * @param session
	 * @return 学习进度
	 */
	private Studyschedule getUserStudyschedule(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Studyschedule studyschedule = new Studyschedule();
		if (user != null) {
			Student student = studentService.selectByStudentNumber(user.getUserName());
			if (student != null)
				studyschedule = studyscheduleService.getByStudentId(student.getStudentId());
		}
		return studyschedule;
	}
	
	@RequestMapping("/modify_password")
	@ResponseBody
	public Json modifyPassword(String oldPassword,String newPassword,String reNewPassword,HttpSession session){
		Json json = new Json();

		User user = (User) session.getAttribute("user");
		// 判断从session获取的user是否为空
		if (user != null) {
			if (!oldPassword.trim().equals("") && !newPassword.equals("")) {
				if(!newPassword.equals(reNewPassword)){
					json.setSuccess(false);
					json.setMsg("确认密码和新密码不一致！");
					return json;
				}
				String oldPasswordMd5 =  DigestUtils.md5DigestAsHex(oldPassword.getBytes());
				// 判断原始密码是否正确
				if (oldPasswordMd5.equals(user.getUserPassword())) {
					String newPasswordMd5 = DigestUtils.md5DigestAsHex(newPassword.getBytes());
					user.setUserPassword(newPasswordMd5);
					// 修改密码
					userService.update(user);
					json.setSuccess(true);
					json.setMsg("成功修改密码！");
				} else {
					json.setMsg("原始密码错误！");
				}
			} else {
				json.setMsg("请不要输入空格！");
			}
		} else {
			json.setMsg("操作有误！");
		}
		return json;
	}
}
