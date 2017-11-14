package com.getset.designpatterns.abstractfactory;

public class XiaomiFactory implements PhoneFactory {
    public Phone producePhone() {
        return new XiaomiPhone();
    }

    public Cable produceCable() {
        return new AndroidCable();
    }

    public Charger produceCharger() {
        return new XiaomiCharger();
    }
}
