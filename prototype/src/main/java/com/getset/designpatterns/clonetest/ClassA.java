package com.getset.designpatterns.clonetest;

public class ClassA implements Cloneable {
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    private int a;

    @Override
    public ClassA clone() throws CloneNotSupportedException {
        return (ClassA) super.clone();
    }
}
