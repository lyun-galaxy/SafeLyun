package com.paly.controller.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.paly.domain.Itempool;
import com.paly.domain.Score;
import com.paly.domain.Student;
import com.paly.domain.User;
import com.paly.interceptor.Token;
import com.paly.service.ExamswitchService;
import com.paly.service.ItempoolService;
import com.paly.service.ScoreService;
import com.paly.service.StudentService;
import com.paly.util.MyJSONUtils;
import com.paly.vo.ItempoolCustom;

/**
 * 在线考试Controller
 * 
 * @author luohuaming
 *
 */
@Controller
@RequestMapping("/client_exam")
public class ExamController {
	@Resource
	private ItempoolService itempoolService;
	@Resource
	private StudentService studentService;
	@Resource
	private ScoreService scoreService;
	@Resource
	private ExamswitchService examswitchService;

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
	@Token(needSaveToken = true)
	public void getEpaper(HttpServletResponse response, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断是否加载试题界面
		User user = (User) session.getAttribute("user");
		int status = itempoolService.isCanExamByUser(user);
		if (status == 1) {
			List<Itempool> choiceList = itempoolService.randomCreateChoiceExams(50);
			// 将考题存入session域中
			session.setAttribute("correctexams", choiceList);
			map.put("choiceList", choiceList);
		}
		map.put("status", status);
		MyJSONUtils.writeJson(map, response);
	}

	/**
	 * 校验答案
	 * 
	 * @return
	 */
	@RequestMapping("/submitTestPaper")
	@Token(needRemoveToken = true)
	public ModelAndView submitTestPaper(ItempoolCustom itempoolCustom, HttpSession session) {
		List<Itempool> choiceList = itempoolCustom.getChoiceList();
		ModelAndView modelAndView = new ModelAndView();
		if (choiceList != null) {
			// 统计分数
			float fration = statistics(choiceList, session);
			// 保存学生成绩
			saveScore(session, fration);
		}else{
			// 保存学生成绩
			saveScore(session, 0);
		}
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
				int scoreMakeupNum = score.getScoreMakeupNum();
				score.setScoreMakeupNum(scoreMakeupNum + 1);
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
		for (int i = 0; myChoices != null && i < myChoices.size() && i < correctexams.size(); i++) {
			Itempool correct = correctexams.get(i);
			Itempool myChoice = myChoices.get(i);
			if (correct.getItempoolCorrect().equalsIgnoreCase(myChoice.getItempoolCorrect())) {
				// TODO 每题的分数
				fration += 2;
			}
		}
		return fration;
	}
}
