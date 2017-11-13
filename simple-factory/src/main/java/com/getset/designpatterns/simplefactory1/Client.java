package com.getset.designpatterns.simplefactory1;

public class Client {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        Shape c = factory.CreateShape("CIRCLE");
        c.draw();
    }
}
