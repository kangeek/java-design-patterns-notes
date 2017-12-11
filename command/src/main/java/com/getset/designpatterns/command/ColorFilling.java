package com.getset.designpatterns.command;

public class ColorFilling implements Command {
    private ColorFiller filler;
    private String arg;

    public ColorFilling(ColorFiller filler, String arg) {
        this.filler = filler;
        this.arg = arg;
    }

    public void doCmd() {
        filler.fillColor(arg);
    }

    public void undoCmd() {
        filler.unfillColor();
    }
}
