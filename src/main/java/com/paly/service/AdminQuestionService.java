package com.paly.service;

import java.util.List;

import com.paly.domain.Itempool;
import com.paly.pageModel.Datagrid;
import com.paly.pageModel.Question;

public interface AdminQuestionService extends BaseService<Itempool>{
	public void getQuestion(String path);
	
	public Question save(Question question);

	public Datagrid datagrid(Question question);  

	public int remove(String ids);

	public Question edit(Question question);
	
	public void audit(String ids);
}
