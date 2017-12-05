package com.getset.designpatterns.facade;

public class Client {
    public static void main(String[] args) {
        ShapeDrawer.drawCircle();
        ShapeDrawer.drawRectangle();
        ShapeDrawer.drawTrangle();
    }
}
