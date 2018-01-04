package com.getset.designpatterns.visitor;

public class Area implements Calculator {
    public double ofShape(Triangle triangle) {
        double a = triangle.getEdgeA(), b = triangle.getEdgeB(), c = triangle.getEdgeC();
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) *  (p - b) * (p - c));
    }

    public double ofShape(Circle circle) {
        return Math.PI * circle.getRadius() * circle.getRadius();
    }

    public double ofShape(Square square) {
        return Math.pow(square.getEdge(), 2);
    }

    public double ofShape(Rectangle rectangle) {
        return rectangle.getWidth() * rectangle.getLength();
    }
}
