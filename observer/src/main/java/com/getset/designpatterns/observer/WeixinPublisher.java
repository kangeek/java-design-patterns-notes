package com.getset.designpatterns.observer;

public class WeixinPublisher extends Publisher {
    private String dingyuehao;

    public WeixinPublisher(String dingyuehao) {
        this.dingyuehao = dingyuehao;
    }

    @Override
    public String toString() {
        return "微信订阅号[" + dingyuehao + "]";
    }
}
