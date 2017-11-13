package com.getset.designpatterns.factorymethod1;

public class Client {
    public static void main(String[] args) {
        ShapeFactory factory = new CircleFactory();
        Shape c = factory.getShape();
        c.draw();
    }
}
