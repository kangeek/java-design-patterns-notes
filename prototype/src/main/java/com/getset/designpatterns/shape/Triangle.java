package com.getset.designpatterns.shape;

public class Triangle extends Shape {
    public Triangle() {
        this.type = "triangle";
    }

    public void draw() {
        System.out.println("Draw a " + type + ", fill with " + fillColor + ", frame color is " + frameColor + ", inner text is [" + innerText + "].");
    }
}
