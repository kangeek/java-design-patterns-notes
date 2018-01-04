package com.getset.designpatterns.visitor;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double accept(Calculator calculator) {
        return calculator.ofShape(this);
    }
}
