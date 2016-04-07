package com.paly.pageModel;

public class Depart {

	private int departId;
	private String departName;
	private boolean selected;

	public Depart() {
		super();
	}

	public Depart(int departId, String departName) {
		super();
		this.departId = departId;
		this.departName = departName;
	}

	public int getDepartId() {
		return departId;
	}

	public void setDepartId(int departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
