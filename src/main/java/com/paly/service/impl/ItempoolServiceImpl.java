package com.paly.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paly.domain.Examswitch;
import com.paly.domain.Itempool;
import com.paly.domain.Score;
import com.paly.domain.Student;
import com.paly.domain.User;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.ItempoolMapper;
import com.paly.service.ExamswitchService;
import com.paly.service.ItempoolService;
import com.paly.service.StudentService;

/**
 * 题库Service接口实现
 * 
 * @author root
 *
 */
@Service("itempoolService")
@Transactional
public class ItempoolServiceImpl extends BaseServiceImpl<Itempool> implements ItempoolService {
	@Resource
	private ItempoolMapper itempoolMapper;
	@Resource
	private ExamswitchService examswitchService;
	@Resource
	private StudentService studentService;
	@Override
	public BaseMapper<Itempool> getBaseMapper() {
		return itempoolMapper;
	}

	@Override
	public List<Itempool> randomCreateChoiceExams(int count) {
		List<Itempool> itempool = itempoolMapper.getIsChecked();
		List<Itempool> result = new ArrayList<Itempool>();
		// 打乱集合
		Collections.shuffle(itempool);
		for (int i = 0; i < count && i < itempool.size(); i++) {
			result.add(itempool.get(i));
		}
		return result;
	}
	/**
	 * 判断用户是否可以考试
	 * @param user 用户
	 * @return 状态：1 可以考试  ，0 不是考试时间 ，2考试已经通过 ，3补考次数已用完
	 */
	public int isCanExamByUser(User user){
		Examswitch examswitch = examswitchService.getExamswitch();
		int status = 0;
		if (examswitch != null && examswitch.getSwitchOnOrOff()) {
			Student student = studentService.selectByStudentNumber(user.getUserName());
			if (student != null) {
				//用户为学生才能考试
				Score score = student.getScore();
				if(score.getScoreMark() >=60){
					status = 2;
				}
				else if(score.getScoreMakeupNum() >=3){
					status = 3;
				}
				else{
					//只有考试分数小于60，补考次数小于等于3次(index从0开始)才能考试
					status = 1;
				}
			}
		}
		return status;
	}
}
