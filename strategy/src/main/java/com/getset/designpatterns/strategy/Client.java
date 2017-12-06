package com.getset.designpatterns.strategy;

public class Client {
    public static void main(String[] args) {
        int x = 6, y = 3;
        Calculator calculator = new Calculator(new AddOperation());
        System.out.println("6+3=" + calculator.calculate(x, y));
        calculator.setOperation(new SubOperation());
        System.out.println("6-3=" + calculator.calculate(x, y));
        calculator.setOperation(new MulOperation());
        System.out.println("6*3=" + calculator.calculate(x, y));
        calculator.setOperation(new DivOperation());
        System.out.println("6/3=" + calculator.calculate(x, y));
    }
}
