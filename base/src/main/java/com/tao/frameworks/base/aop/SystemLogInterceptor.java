package com.tao.frameworks.base.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class SystemLogInterceptor {

    @Autowired
    HttpServletRequest request;

    @Pointcut("execution(* *..*Controller.*(..))")
    public void controllerAspect() {

    }

    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint pjp) {
        log.info("-------------------------------------controller start------------------------------------------------");
        try{
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            request = sra.getRequest();

            String url = request.getRequestURL().toString();
            String method = request.getMethod();
            String uri = request.getRequestURI();
            String queryString = request.getQueryString();
            log.info("Request: url: {"+url+"}, method: {"+method+"}, uri: {"+uri+"}, params: {"+queryString+"}");

            MethodSignature signature = (MethodSignature) pjp.getSignature();
//            Method methodObj = signature.getMethod();
//
//            Object args[] = pjp.getArgs();
//            for (Object arg: args) {
//                log.info(arg.toString());
//            }
            Object result = pjp.proceed();
            log.info("Response: " + result);
            log.info("--------------------------------------controller end-------------------------------------------------");
            return result;
        }catch (Throwable e){
            log.error(e.getMessage());
        }
        return null;
    }
}
