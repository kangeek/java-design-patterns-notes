package com.getset.designpatterns.clonetest;

public class ClassB extends ClassA {
    private String b;

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public void test() {
        System.out.println(super.getClass().getCanonicalName());
    }
}
