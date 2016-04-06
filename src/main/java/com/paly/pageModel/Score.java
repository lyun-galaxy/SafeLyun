package com.paly.pageModel;

public class Score {

	private String no;
	private String name;
	private double score;

	public Score() {
		super();
	}

	public Score(String no, String name, double score) {
		super();
		this.no = no;
		this.name = name;
		this.score = score;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
