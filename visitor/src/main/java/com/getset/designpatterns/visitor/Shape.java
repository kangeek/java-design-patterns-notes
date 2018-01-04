package com.getset.designpatterns.visitor;

public interface Shape {
    double accept(Calculator calculator);
}
