package com.getset.designpatterns.proxy.httpproxy;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        HttpServer overseasHttpServer = new OverseasHttpServer();

        HttpServer httpServer = new HttpProxy(overseasHttpServer);
        httpServer.handleRequest();

        HttpProxyHandler proxy = new HttpProxyHandler(overseasHttpServer);
        HttpServer httpProxy = (HttpServer) Proxy.newProxyInstance(proxy.getClass().getClassLoader(), overseasHttpServer.getClass().getInterfaces(), proxy);
        httpProxy.handleRequest();
    }
}
