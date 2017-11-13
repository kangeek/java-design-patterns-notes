package com.getset.designpatterns.hierafactorymethod;

public class ConcreteSubFactory implements AbstractSubFactory {
    public AbstractProduct create() {
        return new ConcreteSubProduct();
    }
}
