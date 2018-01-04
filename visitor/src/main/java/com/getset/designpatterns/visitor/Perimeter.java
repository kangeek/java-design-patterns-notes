package com.getset.designpatterns.visitor;

public class Perimeter implements Calculator {
    public double ofShape(Triangle triangle) {
        return triangle.getEdgeA() + triangle.getEdgeB() + triangle.getEdgeC();
    }

    public double ofShape(Circle circle) {
        return circle.getRadius() * Math.PI * 2;
    }

    public double ofShape(Square square) {
        return square.getEdge() * 4;
    }

    public double ofShape(Rectangle rectangle) {
        return 2 * (rectangle.getLength() + rectangle.getWidth());
    }
}
