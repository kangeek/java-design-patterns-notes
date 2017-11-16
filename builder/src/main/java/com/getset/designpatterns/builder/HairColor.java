package com.getset.designpatterns.builder;

/**
 * 发色
 */
public enum HairColor {

    WHITE, BLOND, RED, BROWN, BLACK;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
