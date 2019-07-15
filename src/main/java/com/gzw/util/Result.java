package com.gzw.util;

public class Result {
	//错误信息
	private String message;
	//提交事物结果
	private boolean result;
	
	private Object entity;
	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public Object getEntity() {
		return entity;
	}
	public void setEntity(Object entity) {
		this.entity = entity;
	}
	
	public Result() {
		super();
	}
	
	public Result(String message, boolean result, Object entity) {
		super();
		this.message = message;
		this.result = result;
		this.entity = entity;
	}
	
}
