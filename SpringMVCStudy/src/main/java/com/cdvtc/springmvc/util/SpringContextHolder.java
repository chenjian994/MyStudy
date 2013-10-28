package com.cdvtc.springmvc.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created with IntelliJ IDEA.
 * User: chenjian
 * Date: 13-10-21
 * Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }
    public static <T> T getBean(String name) {
       checkApplicationContext();
        return (T)applicationContext.getBean(name);
    }
    public static <T> T getBean(Class<T> clazz) {
       checkApplicationContext();
        return (T)applicationContext.getBeansOfType(clazz);
    }
    public static void cleanApplicationContext() {
        applicationContext = null;
    }
    private static void checkApplicationContext() {
        if(null == applicationContext)
            throw new IllegalStateException("ApplicationContext尚未注入");
    }
}
