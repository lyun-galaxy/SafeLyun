package com.paly.pageModel;

public class Section extends BaseModel {

	private String id;
	private String chapterId;
	private String name;
	private String context;

	public Section() {
		super();
	}

	public Section(String id, String chapterId, String name, String context) {
		super();
		this.id = id;
		this.chapterId = chapterId;
		this.name = name;
		this.context = context;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChapterId() {
		return chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}
