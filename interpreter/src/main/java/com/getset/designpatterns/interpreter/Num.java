package com.getset.designpatterns.interpreter;

public class Num implements Expression {
    private int number;

    public Num(int number) {
        this.number = number;
    }

    public int interpret() {
        return number;
    }
}
