package com.paly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Section;
import com.paly.domain.Student;
import com.paly.domain.Studyschedule;
import com.paly.domain.Subsection;
import com.paly.domain.User;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.StudentMapper;
import com.paly.mapper.StudyscheduleMapper;
import com.paly.mapper.SubsectionMapper;
import com.paly.service.SectionService;
import com.paly.service.SubsectionService;

/**
 * 子章节Service实现
 * 
 * @author luohuaming
 *
 */
@Service("subsectionService")
public class SubsectionServiceImpl extends BaseServiceImpl<Subsection> implements SubsectionService {
	@Resource
	private SubsectionMapper subsectionMapper;
	@Resource
	private StudentMapper studentMapper;
	@Resource
	private StudyscheduleMapper studyscheduleMapper;
	@Resource
	private SectionService sectionService;

	@Override
	public BaseMapper<Subsection> getBaseMapper() {
		return subsectionMapper;
	}

	@Override
	public List<Subsection> getBySectionId(int sectionId) {
		return subsectionMapper.getBySectionId(sectionId);
	}

	@Override
	public Subsection getByStudyscheduleId(int studyscheduleId) {
		return subsectionMapper.getByStudyscheduleId(studyscheduleId);
	}

	@Override
	public Subsection getFirstBySectionId(int sectionId) {
		List<Subsection> list = subsectionMapper.getBySectionId(sectionId);
		if (!list.isEmpty()) {
			return subsectionMapper.getBySectionId(sectionId).get(0);
		}
		return null;
	}

	@Override
	public Subsection getUserSubsection(User user) {
		Studyschedule studyschedule = new Studyschedule();
		Student student = null;
		if (user != null) {
			// 获取用户所属的学生
			student = studentMapper.selectByStudentNumber(user.getUserName());
			if (student != null) {
				// 获取学生的学习进度
				studyschedule = studyscheduleMapper.getByStudentId(student.getStudentId());
			}
		}
		if (studyschedule == null) {
			// 如果学习进度为空，返回第一章的第一小节，并保存学习进度
			Subsection subsection = getBySubsectionCode(1);
			studyschedule = new Studyschedule(0, subsection, student);
			studyscheduleMapper.insert(studyschedule);
			return studyschedule.getSubsection();
		} else {
			// 返回当前学习的小节
			return studyschedule.getSubsection();
		}
	}

	@Override
	public Subsection getBySubsectionCode(int code) {
		return subsectionMapper.querySubsectionByCode(code);
	}

	@Override
	public List<Subsection> queryIsChecked() {
		return subsectionMapper.queryIsChecked();
	}

	@Override
	public List<Subsection> queryIsNotChecked() {
		return subsectionMapper.queryIsNotChecked();
	}

	@Override
	public List<Subsection> queryIsNotCheckedBySecId(int sectionId) {
		return subsectionMapper.queryIsNotCheckedBySecId(sectionId);
	}

	@Override
	public List<Subsection> queryIsCheckedBySecId(int sectionId) {
		return subsectionMapper.queryIsCheckedBySecId(sectionId);
	}
}
