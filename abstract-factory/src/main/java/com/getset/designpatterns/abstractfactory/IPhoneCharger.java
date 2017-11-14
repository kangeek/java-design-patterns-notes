package com.getset.designpatterns.abstractfactory;

public class IPhoneCharger implements Charger {
    public void charge() {
        System.out.println("Charge with iPhone charger. ");
    }
}
