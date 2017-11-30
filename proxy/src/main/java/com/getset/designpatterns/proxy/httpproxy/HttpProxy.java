package com.getset.designpatterns.proxy.httpproxy;

public class HttpProxy implements HttpServer {
    private HttpServer overseasHttpServer;

    public HttpProxy(HttpServer overseasHttpServer) {
        this.overseasHttpServer = overseasHttpServer;
    }

    public void handleRequest() {
        preRequest();
        this.overseasHttpServer.handleRequest();
        postRequest();
    }

    public void preRequest() {
        System.out.println("拦截客户端的HTTP请求，发送至海外的代理服务器，由其向海外HTTP服务器发送请求...");
    }
    public void postRequest() {
        System.out.println("海外代理服务其接收海外HTTP服务器反馈的结果，发送回客户端浏览器进行显示...");
    }
}
