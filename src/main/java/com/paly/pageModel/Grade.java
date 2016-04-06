package com.paly.pageModel;

public class Grade {

	private int gradeId;
	private String gradeName;
	private boolean selected;

	public Grade() {
		super();
	}

	public Grade(int gradeId, String gradeName, boolean selected) {
		super();
		this.gradeId = gradeId;
		this.gradeName = gradeName;
		this.selected = selected;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
