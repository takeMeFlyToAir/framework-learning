package com.zzr.sso.core.entity;

import java.io.Serializable;

/**
 *
 * @param <T>
 */
public class ReturnResult<T> implements Serializable {
	public static final long serialVersionUID = 42L;

	public static final int SUCCESS_CODE = 200;
	public static final int FAIL_CODE = 500;
	public static final ReturnResult<String> SUCCESS = new ReturnResult<String>(null);
	public static final ReturnResult<String> FAIL = new ReturnResult<String>(FAIL_CODE, null);

	private int code;
	private String msg;
	private T data;

	public ReturnResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public ReturnResult(T data) {
		this.code = SUCCESS_CODE;
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

}
