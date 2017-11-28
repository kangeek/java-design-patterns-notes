package com.getset.designpatterns.adaptor;

public class Computer {

    protected String ppt = "My PPT report";

    public String transmitWithVGA() {
        return ppt;
    }
}
