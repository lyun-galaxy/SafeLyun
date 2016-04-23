package com.paly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.paly.domain.Examswitch;
import com.paly.mapper.BaseMapper;
import com.paly.mapper.ExamswitchMapper;
import com.paly.service.AdminExamSwichService;

@Service
public class AdminExamSwichServiceImpl extends BaseServiceImpl<Examswitch> implements AdminExamSwichService {

	@Resource
	ExamswitchMapper examswitchMapper;
	
	@Override
	public void changFlag(Boolean flag) {
		// TODO Auto-generated method stub
		Examswitch examswitch = examswitchMapper.selectByPrimaryKey(1);
		if(flag){
		    examswitch.setSwitchOnOrOff(false);
		}else{
			examswitch.setSwitchOnOrOff(true);
		}
        examswitchMapper.updateByPrimaryKey(examswitch);
	}

	@Override
	public BaseMapper<Examswitch> getBaseMapper() {
		// TODO Auto-generated method stub
		return examswitchMapper;
	}

	@Override
	public boolean getFlag() {
		// TODO Auto-generated method stub
		return examswitchMapper.selectByPrimaryKey(1).getSwitchOnOrOff();
	}

}
