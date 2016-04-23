package com.paly.domain;

public class Json implements java.io.Serializable {

	private boolean success = false;
	private String msg = "";
	private Object obj = null;
    
	public Json() {
	}

	/**
	 * 构造函数
	 * 
	 * @param success
	 *            请求是否成功
	 * @param msg
	 *            请求提示信息
	 * @param obj
	 *            请求结果数据
	 */
	public Json(boolean success, String msg, Object obj) {
		this.success = success;
		this.msg = msg;
		this.obj = obj;
	}

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

}
