package com.getset.designpatterns.proxy.httpproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HttpProxyHandler implements InvocationHandler {
    private Object obj;

    public HttpProxyHandler(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        preRequest();
        System.out.println(proxy.getClass().getName());
        Object o = method.invoke(obj, args);
        postRequest();
        return o;
    }

    public void preRequest() {
        System.out.println("拦截客户端的HTTP请求，发送至海外的代理服务器，由其向海外HTTP服务器发送请求...");
    }
    public void postRequest() {
        System.out.println("海外代理服务其接收海外HTTP服务器反馈的结果，发送回客户端浏览器进行显示...");
    }
}
