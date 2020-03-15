package com.tao.frameworks.base.exception;
/**
 * 自定义异常
 *
 * @author tao
 */
public class CustomException extends RuntimeException {

    private Integer code = -1;

    public CustomException() {
        super();
    }

    public CustomException(ErrorCode code) {
        super(code.getMessage());
        this.code = code.getCode();
    }

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
