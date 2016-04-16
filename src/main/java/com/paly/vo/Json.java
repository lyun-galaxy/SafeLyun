package com.paly.vo;

/**
 * 
 * @author Royal
 *
 */
public class Json implements java.io.Serializable {

	private boolean success = false;
	private String msg = "";
	private Object obj = null;
	private boolean flag = false;

	public boolean isSuccess() {
		return success;
	}

	public String getMsg() {
		return msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
