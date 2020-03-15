package com.tao.frameworks.base.spring;

import com.tao.frameworks.base.common.Result;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomRequestResponseBodyMethodProcessor extends RequestResponseBodyMethodProcessor {

    private BeanFactory beanFactory;
    private final static InvocableResultHandler NULL_HANDLER = InvocableResultHandler.NULL_METHOD;
    private final static Map<Class<?>, InvocableResultHandler> RESULT_HANDLE_METHOD = new ConcurrentHashMap<Class<?>, InvocableResultHandler>();

    public CustomRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    public CustomRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters, ContentNegotiationManager manager, BeanFactory beanFactory) {
        super(converters, manager);
        this.beanFactory = beanFactory;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
        mavContainer.setRequestHandled(true);
        Object result = wrapReturnValue(returnType, returnValue);
        if(result!=null){
            writeWithMessageConverters(result, returnType, webRequest);
        }else if(returnValue!=null){
            writeWithMessageConverters(returnValue, returnType, webRequest);
        }
    }

    private Object wrapReturnValue(MethodParameter returnType, Object returnValue) {

        InvocableResultHandler handler = getHandler(returnType);

        if (handler == NULL_HANDLER) {
            return doDefaultWrap(returnValue);
        }
        try {
            return handler.invoke(returnType, returnValue);
        } catch (Exception e) {
            return returnValue;
        }
    }

    private Object doDefaultWrap(Object returnValue) {
        if (returnValue == null) {
            return new Result();
        }
        if (isWrapped(returnValue)) {
            return returnValue;
        } else {
            return new Result(returnValue);
        }
    }

    private boolean isWrapped(Object returnValue) {
        return (returnValue instanceof Result) || isMyObject(returnValue);
    }

    private boolean isMyObject(Object returnValue) {
        Class<?> clz = returnValue.getClass();
        while (clz != null) {
            if (Result.class == clz) {
                return true;
            }
            clz = clz.getSuperclass();
        }
        return false;
    }

    private InvocableResultHandler getHandler(MethodParameter returnType) {

        Class<?> clz = returnType.getDeclaringClass();

        InvocableResultHandler handler = RESULT_HANDLE_METHOD.get(clz);
        if (handler != null) {
            return handler;
        }
        Object bean = beanFactory.getBean(clz);
        if (bean == null) {
            return resolveDefault(clz);
        }
        for (Method method : clz.getMethods()) {
            if (AnnotationUtils.findAnnotation(method, CustomResultHandler.class) != null) {
                InvocableResultHandler _handler =
                        new InvocableResultHandler(bean, BridgeMethodResolver.findBridgedMethod(method));
                RESULT_HANDLE_METHOD.put(clz, _handler);
                return _handler;
            }
        }
        return resolveDefault(clz);
    }

    private InvocableResultHandler resolveDefault(Class<?> clz) {
        RESULT_HANDLE_METHOD.put(clz, NULL_HANDLER);
        return NULL_HANDLER;
    }

}
