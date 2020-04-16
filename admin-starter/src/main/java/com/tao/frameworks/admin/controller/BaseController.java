package com.tao.frameworks.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.tao.frameworks.admin.exception.CustomException;
import com.tao.frameworks.admin.exception.ErrorCode;
import com.tao.frameworks.admin.tools.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.management.ReflectionException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j

/**
 * 异常处理
 *
 * @author tao
 */
public class BaseController {

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public Result errorHandler(HttpServletRequest request, Exception exception) {
        log.error("{}请求失败, url:{}, method:{}, ip: {}, params: {}, header: {},",
                this.getClass().getName(),
                request.getRequestURI(),
                request.getMethod(),
                getIp(request),
                JSONObject.toJSONString(request.getParameterMap()),
                JSONObject.toJSONString((headerMap(request))), exception);
        if (exception instanceof MissingServletRequestParameterException) {
            return new Result(ErrorCode.paramError);
        }

        if (exception instanceof NullPointerException) {
            return new Result(ErrorCode.nullPointer);
        }

        if (exception instanceof SQLException) {
            return new Result(ErrorCode.sqlError);
        }

        if (exception instanceof ReflectionException) {
            return new Result(ErrorCode.sqlError);
        }

        if (exception instanceof RuntimeException) {
            if (exception instanceof CustomException) {
                return new Result(((CustomException) exception).getCode(), exception.getMessage());
            } else {
                return new Result(exception.getMessage());
            }
        }
        return new Result("请求失败");
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        if (request.getHeader("X-Forwarded-For") != null) {
            ip = request.getHeader("X-Forwarded-For");
        } else if (request.getHeader("X-Real-IP") != null) {
            ip = request.getHeader("X-Real-IP");
        }
        return ip;
    }

    public static Map<String, String> headerMap(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headerMap = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            headerMap.put(header, request.getHeader(header));
        }
        return headerMap;
    }
}
