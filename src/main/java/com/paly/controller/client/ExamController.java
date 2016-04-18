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
import com.paly.domain.Score;
import com.paly.domain.Student;
import com.paly.domain.User;
import com.paly.service.ItempoolService;
import com.paly.service.ScoreService;
import com.paly.service.StudentService;
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
	@Resource
	private StudentService studentService;
	@Resource
	private ScoreService scoreService;

	/**
	 * 跳转到考试界面
	 * 
	 * @return
	 */
	@RequestMapping("/toExamPage")
	public String toExamPage() {
		return "client/exam.jsp";
	}

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
		float fration = statistics(itempoolCustom.getChoiceList(), session);
		ModelAndView modelAndView = new ModelAndView();
		// 保存学生成绩
		saveScore(session, fration);
		// 重定向到成绩界面
		modelAndView.setViewName("redirect:/client_score/toScoreUI.action");
		return modelAndView;
	}

	/**
	 * 保存学生成绩
	 * 
	 * @param session
	 *            session域,需从session域中获取数据
	 * @param fration
	 *            成绩
	 */
	private void saveScore(HttpSession session, float fration) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			// 获取用户所属学生
			Student student = studentService.selectByStudentNumber(user.getUserName());
			if (student != null) {
				// 保存学生成绩
				Score score = student.getScore();
				// 从session域获取补考次数
				int scoreMakeupNum = 0;
				if (session.getAttribute("scoreMakeupNum") != null) {
					scoreMakeupNum = (int) session.getAttribute("scoreMakeupNum");
				}
				score.setScoreMakeupNum(scoreMakeupNum);
				score.setScoreMark(fration);
				score.setStudent(student);
				scoreService.update(score);
			}
		}
	}

	/**
	 * 统计分数
	 * 
	 * @param myChoices
	 *            考生的答案
	 * @param session
	 *            需从session域中获取正确答案
	 * @return 总分
	 */
	@SuppressWarnings("unchecked")
	private float statistics(List<Itempool> myChoices, HttpSession session) {
		float fration = 0;
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
