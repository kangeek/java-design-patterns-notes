package com.getset.designpatterns.abstractfactory;

public class IPhoneCable implements Cable {
    public void transmit() {
        System.out.println("Transmit data or power with iPhone Cable. ");
    }
}
