package com.getset.designpatterns.decorator;

public abstract class BoardStringDisplay extends Display {
    protected Display display;
    protected BoardStringDisplay(Display display) {
        this.display = display;
    }
}
