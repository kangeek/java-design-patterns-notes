package com.getset.designpatterns.visitor;

public interface Calculator {
    double ofShape(Triangle triangle);
    double ofShape(Circle circle);
    double ofShape(Square square);
    double ofShape(Rectangle rectangle);
}
