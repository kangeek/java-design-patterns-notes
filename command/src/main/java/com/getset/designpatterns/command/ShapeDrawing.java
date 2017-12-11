package com.getset.designpatterns.command;

public class ShapeDrawing implements Command {
    private ShapeDrawer drawer;
    private String arg;

    public ShapeDrawing(ShapeDrawer drawer, String arg) {
        this.drawer = drawer;
        this.arg = arg;
    }

    public void doCmd() {
        drawer.drawShape(arg);
    }

    public void undoCmd() {
        drawer.undrawShape();
    }
}
