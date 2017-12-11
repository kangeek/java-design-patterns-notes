package com.getset.designpatterns.command;

public class ShapeDrawer {
    public void drawShape(String shape) {
        System.out.println("画了一个" + shape);
    }
    public void undrawShape() {
        System.out.println("撤销刚才画的形状");
    }
}
