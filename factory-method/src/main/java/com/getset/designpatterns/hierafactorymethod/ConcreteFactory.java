package com.getset.designpatterns.hierafactorymethod;

public class ConcreteFactory implements AbstractFactory {
    public AbstractProduct create() {
        return new ConcreteProduct();
    }
}
