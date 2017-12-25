package com.getset.designpatterns.interpreter;

public class Mul implements Expression {
    private Expression left, right;

    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret() {
        return left.interpret() * right.interpret();
    }
}
