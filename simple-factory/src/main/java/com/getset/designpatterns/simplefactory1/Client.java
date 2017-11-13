package com.getset.designpatterns.simplefactory1;

public class Client {
    public static void main(String[] args) {
        Shape c = ShapeFactory.CreateShape("CIRCLE");
        c.draw();
    }
}
