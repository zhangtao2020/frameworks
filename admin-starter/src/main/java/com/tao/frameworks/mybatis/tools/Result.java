package com.tao.frameworks.mybatis.tools;

import com.tao.frameworks.mybatis.exception.ErrorCode;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 统一返回类
 *
 * @author tao
 */
@Data
@Accessors(chain = true)
public class Result {

	/**
	 * 状态码
	 */
	private int code;

	/**
	 * 描述
	 */
	private String msg;

	/**
	 * 总数
	 */
	private int count = 0;

	/**
	 * 数据
	 */
	private Object data;


	public Result(ErrorCode errorCode){
		this(errorCode.getCode(), errorCode.getMessage(), 0, null);
	}

	public Result(Integer code, String message, int count, Object data) {
		this.code = code;
		this.msg = message;
		this.count = count;
		this.data = data;
	}

	public Result(Integer code, String message) {
		this(code, message, 0, null);
	}

	public Result(String message) {
		this(-1, message, 0, null);
	}

	public Result(){
		this(0, "success", 0, null);
	}

	public Result(Object data){
		this(0, "success", 0, data);
	}

	public static Result buildSuccess(Object data){
		return new Result(0, "success", 0, data);
	}


}
