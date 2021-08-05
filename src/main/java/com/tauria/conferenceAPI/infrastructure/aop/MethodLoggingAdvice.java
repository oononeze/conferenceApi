package com.tauria.conferenceAPI.infrastructure.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MethodLoggingAdvice implements MethodInterceptor {

    Logger logger = LoggerFactory.getLogger(MethodLoggingAdvice.class);

    @Nullable
    @Override
    public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {


       /* StringBuilder authorities = new StringBuilder();
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        auth.getAuthorities().forEach(x -> authorities.append(x.getAuthority()).append(","));

        logger.info("USER: "+ auth.getPrincipal().toString().toUpperCase() +
                " with authority: " + authorities +
                "calling: " + invocation.getMethod().getDeclaringClass().getSimpleName());*/

        logger.info("Calling method: " + invocation.getMethod()
                .getDeclaringClass().getSimpleName());

        Object retVal = invocation.proceed();

        /*logger.info("Call to: " + invocation.getMethod()
                .getDeclaringClass().getSimpleName() +
                " Method executed successfully. for USER: "
                + auth.getPrincipal().toString().toUpperCase());*/

        logger.info("Call to: " + invocation.getMethod()
                .getDeclaringClass().getSimpleName() +
                " Method executed successfully.");

        return retVal;

    }
}
