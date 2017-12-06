package com.getset.designpatterns.strategy;

public class MulOperation implements Operation {
    public int calculate(int x, int y) {
        return x * y;
    }
}
