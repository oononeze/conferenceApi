package com.tauria.conferenceAPI.infrastructure.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

import javax.servlet.ServletException;
import java.lang.reflect.Method;

public class ThrowsErrorAdvice implements ThrowsAdvice {

    Logger logger = LoggerFactory.getLogger(ThrowsErrorAdvice.class);

    public void afterThrowing(Method method, Object args,
                              Object target, Exception ex) throws Throwable {
        logger.error("Generic Exception Capture Caught: "
                + ex.getClass().getName() +
        " Method: " + method.getName());
    }

    public void afterThrowing(Method method, Object args,
                              Object target, ServletException ex) throws Throwable {
        logger.error("Generic Exception Capture Caught: "
                + ex.getClass().getName() +
                " Method: " + method.getName());
    }

}
