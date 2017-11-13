package com.getset.designpatterns.simplefactory1;

public class ShapeFactory {
    public Shape CreateShape(String shape) {
        if (null == shape) {
            return null;
        }

        if (shape.equals("CIRCLE")) {
            return new Circle();
        } else if (shape.equals("RECTANGLE")) {
            return new Rectangle();
        } else if (shape.equals("TRIANGLE")) {
            return new Triangle();
        }

        return null;
    }
}
