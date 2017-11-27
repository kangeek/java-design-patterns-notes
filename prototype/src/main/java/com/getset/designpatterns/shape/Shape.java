package com.getset.designpatterns.shape;

public abstract class Shape implements Cloneable {
    protected String type;
    protected String fillColor;
    protected String frameColor;
    protected String innerText;

    @Override
    public Shape clone() throws CloneNotSupportedException {
        return (Shape)super.clone();
    }

    public abstract void draw();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public String getFrameColor() {
        return frameColor;
    }

    public void setFrameColor(String frameColor) {
        this.frameColor = frameColor;
    }

    public String getInnerText() {
        return innerText;
    }

    public void setInnerText(String innerText) {
        this.innerText = innerText;
    }
}
