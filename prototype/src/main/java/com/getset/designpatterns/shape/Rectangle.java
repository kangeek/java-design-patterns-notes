package com.getset.designpatterns.shape;

public class Rectangle extends Shape {
    public Rectangle() {
        this.type = "rectangle";
    }

    public void draw() {
        System.out.println("Draw a " + type + ", fill with " + fillColor + ", frame color is " + frameColor + ", inner text is [" + innerText + "].");
    }
}
