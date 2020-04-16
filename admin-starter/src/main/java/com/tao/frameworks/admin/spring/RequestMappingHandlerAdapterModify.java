package com.tao.frameworks.admin.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import java.util.ArrayList;
import java.util.List;

public class RequestMappingHandlerAdapterModify implements BeanFactoryAware, InitializingBean {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        RequestMappingHandlerAdapter bean = beanFactory.getBean(RequestMappingHandlerAdapter.class);
        this.modifyReturnValueHandlers(bean);
    }

    private void modifyReturnValueHandlers(RequestMappingHandlerAdapter bean){
        List<HandlerMethodReturnValueHandler> newList = new ArrayList<>();
        for (HandlerMethodReturnValueHandler handler : bean.getReturnValueHandlers()){
            if(handler instanceof RequestResponseBodyMethodProcessor){
                newList.add(new CustomRequestResponseBodyMethodProcessor(bean.getMessageConverters(), beanFactory.getBean("mvcContentNegotiationManager", ContentNegotiationManager.class), beanFactory));
            }else{
                newList.add(handler);
            }
        }
        bean.setReturnValueHandlers(newList);
    }
}
