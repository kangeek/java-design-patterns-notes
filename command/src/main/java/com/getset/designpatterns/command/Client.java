package com.getset.designpatterns.command;

public class Client {
    public static void main(String[] args) {
        DrawingApp app = new DrawingApp();
        ShapeDrawer shapeDrawer = new ShapeDrawer();
        ColorFiller colorFiller = new ColorFiller();
        Command drawCircle = new ShapeDrawing(shapeDrawer, "圆形");
        Command fillRed = new ColorFilling(colorFiller, "红色");
        Command drawRectancle = new ShapeDrawing(shapeDrawer, "矩形");
        app.takeCommand(drawCircle);
        app.takeCommand(fillRed);
        app.takeCommand(drawRectancle);
        app.CommandsDone();
        app.undoLastCommand();
    }
}
