package com.getset.designpatterns.proxy.httpproxy;

public class OverseasHttpServer implements HttpServer {
    public void handleRequest() {
        System.out.println("海外的HTTP服务器处理请求并返回结果...");
    }
}
