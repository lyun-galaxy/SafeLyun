package com.paly.pageModel;

import java.io.File;
import java.util.Date;

/**
 * 
 * @author Royal
 *
 */
public class Question extends BaseModel {

	private String id;
	private String title;
	private String a;
	private String b;
	private String c;
	private String d;
	private String answer;
	private Date createDateTime;
    private Boolean status;
	private File file;
	private String name;
	private String uploadName;

	public Question() {
		super();
	}

	public Question(String id, String title, String a, String b, String c, String d, String answer, Date createDateTime) {
		super();
		this.id = id;
		this.title = title;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.answer = answer;
		this.createDateTime = createDateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUploadName() {
		return uploadName;
	}

	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}

}
