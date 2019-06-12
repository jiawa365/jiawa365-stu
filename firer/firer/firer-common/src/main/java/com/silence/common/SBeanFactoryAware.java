package com.silence.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * 获取容器中的bean
 */
@Component
public class SBeanFactoryAware implements BeanFactoryAware {

    public static BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SBeanFactoryAware.beanFactory=beanFactory;
    }
}
