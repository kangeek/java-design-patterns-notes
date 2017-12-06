package com.getset.designpatterns.strategy;

public class Calculator {
    private Operation operation;

    public Calculator(Operation operation) {
        this.operation = operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int calculate(int x, int y) {
        return operation.calculate(x, y);
    }
}
