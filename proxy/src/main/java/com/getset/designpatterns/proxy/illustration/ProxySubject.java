package com.getset.designpatterns.proxy.illustration;

public class ProxySubject implements Subject {
    private Subject realSubject;
    public ProxySubject(Subject realSubject) {
        this.realSubject = realSubject;
    }
    public void request() {
        preRequest();
        this.realSubject.request();
        postRequest();
    }
    public void preRequest() {
        System.out.println("请求前...");
    }
    public void postRequest() {
        System.out.println("请求后...");
    }
}
