package com.getset.designpatterns.proxy.illustration;

public class Client {
    public static void main(String[] args) {
        Subject proxySubject = new ProxySubject(new RealSubject());
        proxySubject.request();
    }
}
