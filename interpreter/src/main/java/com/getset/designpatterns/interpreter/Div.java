package com.getset.designpatterns.interpreter;

public class Div implements Expression {
    private Expression left, right;

    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret() {
        return left.interpret() / right.interpret();
    }
}
