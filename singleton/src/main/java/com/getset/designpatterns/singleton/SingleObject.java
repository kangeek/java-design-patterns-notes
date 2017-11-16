package com.getset.designpatterns.singleton;

public class SingleObject {

    private final static SingleObject INSTANCE = new SingleObject();

    private SingleObject() {}

    public static SingleObject getInstance() {
        return INSTANCE;
    }
}
