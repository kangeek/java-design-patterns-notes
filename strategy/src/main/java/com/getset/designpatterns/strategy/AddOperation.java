package com.getset.designpatterns.strategy;

public class AddOperation implements Operation {
    public int calculate(int x, int y) {
        return x + y;
    }
}
