package com.getset.designpatterns.factorymethod1;

public class TriangleFactory implements ShapeFactory {
    public Shape getShape() {
        return new Triangle();
    }
}
