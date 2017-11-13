package com.getset.designpatterns.hierafactorymethod;

public class Client {
    public static void main(String[] args) {
        AbstractFactory factory = new ConcreteSubFactory();
        AbstractProduct product = factory.create();
        System.out.println(product.getClass().getCanonicalName());
    }
}
