package com.tao.frameworks.base.common;

import com.tao.frameworks.base.exception.ErrorCode;

import java.util.Date;

/**
 * 统一返回类
 *
 * @author tao
 */
public class Result {

	/**
	 * 状态码
	 */
	private int code;

	/**
	 * 描述
	 */
	private String message;

	/**
	 * 时间
	 */
	private Long time;

	/**
	 * 数据
	 */
	private Object data;


	public Result(ErrorCode errorCode){
		this(errorCode.getCode(), errorCode.getMessage(), null);
	}

	public Result(Integer code, String message, Object data) {
		if (code == null) {
			code = -1;
		}
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Result(Integer code, String message) {
		this(code, message, null);
	}

	public Result(String message) {
		this(-1, message, null);
	}

	public Result(){
		this(null, null, null);
	}

	public Result(Object data){
		this(0, "success", data);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Long getTime() {
		time = System.currentTimeMillis() / 1000;
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public static Result buildSuccess(Object data) {
		Result result = new Result(0, "success", data);
		return result;
	}
}
