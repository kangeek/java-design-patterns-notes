package com.getset.designpatterns.abstractfactory;

public class IPhoneFactory implements PhoneFactory {
    public Phone producePhone() {
        return new IPhone();
    }

    public Cable produceCable() {
        return new IPhoneCable();
    }

    public Charger produceCharger() {
        return new IPhoneCharger();
    }
}
