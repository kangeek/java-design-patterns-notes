package com.getset.designpatterns.abstractfactory;

public interface PhoneFactory {
    Phone producePhone();
    Cable produceCable();
    Charger produceCharger();
}
