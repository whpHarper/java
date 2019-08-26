package com.java.guice.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author whp 19-8-26
 * @describ
 */
public class HelloInterceptor implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method=methodInvocation.getMethod();
        String        invokingClass     = method.getDeclaringClass().getSimpleName();
        String        invokedMethodName = method.getName();
        System.out.println("the class:"+invokingClass+" declared the method:" +invokedMethodName);
        Object response = methodInvocation.proceed();
        return response;
    }
}
