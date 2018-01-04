package com.getset.designpatterns.visitor;

public class Square implements Shape {
    private double edge;

    public Square(double edge) {
        this.edge = edge;
    }

    public double getEdge() {
        return edge;
    }

    public double accept(Calculator calculator) {
        return calculator.ofShape(this);
    }
}
