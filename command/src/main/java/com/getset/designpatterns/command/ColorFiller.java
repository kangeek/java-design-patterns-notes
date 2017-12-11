package com.getset.designpatterns.command;

public class ColorFiller {
    public void fillColor(String color) {
        System.out.println("填充" + color);
    }
    public void unfillColor() {
        System.out.println("撤销填充的颜色");
    }
}
