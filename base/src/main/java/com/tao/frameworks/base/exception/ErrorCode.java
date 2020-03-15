package com.tao.frameworks.base.exception;
/**
 * 错误码
 *
 * @author tao
 */
public enum ErrorCode {

    httpStatusNotOk(-200, "服务器请求超时,请稍后重试"),

    socketTimeOut(-201, "服务器响应超时,请稍后重试"),

    connectTimeOut(-202, "服务器请求超时,请稍后重试"),

    nullPointer(-1, "内部错误！"),

    noLogin(-401, "未登录！"),

    noPermission(-402, "无权操作！"),

    paramError(-403, "参数错误！"),

    sqlError(-404, "数据错误！"),

    dbDuplicateError(-405, "记录已存在！");

    int code;
    String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
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

    public static boolean isHttpConnectError(Integer code) {
        return code != null
                && (code.equals(ErrorCode.httpStatusNotOk.getCode())
                || code.equals(ErrorCode.connectTimeOut.getCode())
                || code.equals(ErrorCode.socketTimeOut.getCode()));
    }
}
