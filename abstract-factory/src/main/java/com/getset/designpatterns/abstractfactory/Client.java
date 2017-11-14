package com.getset.designpatterns.abstractfactory;

public class Client {
    public static void main(String[] args) {
        PhoneFactory factory = new IPhoneFactory();
        factory.produceCable().transmit();
        factory.produceCharger().charge();
        factory.producePhone().use();
    }
}
