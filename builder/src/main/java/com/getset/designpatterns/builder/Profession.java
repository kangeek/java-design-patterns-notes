package com.getset.designpatterns.builder;

/**
 * 职业
 */
public enum Profession {
    WARRIOR, THIEF, MAGE, PRIEST;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
