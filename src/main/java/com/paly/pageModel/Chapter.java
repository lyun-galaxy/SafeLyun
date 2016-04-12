package com.paly.pageModel;

public class Chapter extends BaseModel{

//	private int page;
//	private int rows;
//	private String sort;
//	private String order;
//	private String ids;

	private String id;
	private String name;
	private boolean status;

	public Chapter() {
		super();
	}

	public Chapter(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}



}
