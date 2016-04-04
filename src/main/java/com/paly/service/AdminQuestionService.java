package com.paly.service;

import java.util.List;

import com.paly.pageModel.Datagrid;
import com.paly.pageModel.Question;

public interface AdminQuestionService {
	public List<Question> getQuestion(String path);
	
	public Question save(Question question);

	public Datagrid datagrid(Question question);  

	public int remove(String ids);

	public Question edit(Question question);
}
