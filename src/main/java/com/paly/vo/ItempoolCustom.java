package com.paly.vo;

import java.util.List;

import com.paly.domain.Itempool;

/**
 * 试题包装类
 * 
 * @author luohuaming
 *
 */
public class ItempoolCustom {
	private List<Itempool> choiceList;

	public List<Itempool> getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(List<Itempool> choiceList) {
		this.choiceList = choiceList;
	}
}
