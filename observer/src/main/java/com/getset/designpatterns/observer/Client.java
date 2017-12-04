package com.getset.designpatterns.observer;

public class Client {
    public static void main(String[] args) {
        WeixinPublisher publisher = new WeixinPublisher("享学IT");
        publisher.addSubscriber(new WeixinAccount("张三"));
        publisher.addSubscriber(new WeixinAccount("李四"));
        publisher.addSubscriber(new WeixinAccount("王五"));

        publisher.publishMessage("Java设计模式百例-观察者模式");
    }
}
