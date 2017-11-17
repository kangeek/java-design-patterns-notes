package com.getset.designpatterns.clonetest;

import java.io.Serializable;

public class EarTag implements Cloneable, Serializable {
    private int id;
    private String color;

    public EarTag(int id, String color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public EarTag clone() throws CloneNotSupportedException {
        return (EarTag) super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof EarTag))
            return false;
        EarTag earTag = (EarTag) obj;
        return earTag.id == this.id && earTag.color.equals(this.color);
    }
}
