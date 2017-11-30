package com.getset.designpatterns.proxy.illustration;

public class RealSubject implements Subject {
    public void request() {
        System.out.println("处理请求...");
    }
}
