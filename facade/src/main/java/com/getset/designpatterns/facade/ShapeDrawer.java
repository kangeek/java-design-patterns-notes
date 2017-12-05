package com.getset.designpatterns.facade;

public class ShapeDrawer {
    private ShapeDrawer() {}
    public static void drawCircle() {
        new Circle().draw();
    }
    public static void drawTrangle() {
        new Triangle().draw();
    }
    public static void drawRectangle() {
        new Rectangle().draw();
    }
}
