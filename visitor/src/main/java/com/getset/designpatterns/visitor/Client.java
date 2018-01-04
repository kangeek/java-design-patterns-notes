package com.getset.designpatterns.visitor;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        // 一个含有5个元素的List，包含三种不同的形状
        List<Shape> shapes = new ArrayList<Shape>();
        shapes.add(new Triangle(1.3, 2.2, 3.1));
        shapes.add(new Circle(1.2));
        shapes.add(new Triangle(2.4, 3.3, 4.2));
        shapes.add(new Rectangle(2.1, 3.2));
        shapes.add(new Circle(5.6));

        // 计算周长和面积的不同策略（访问者）
        Perimeter perimeter = new Perimeter();
        Area area = new Area();

        // 将周长和面积的计算策略传入（接受不同访问者的访问）
        for (Shape shape : shapes) {
            System.out.printf("周长 : %5.2f\t 面积 : %5.2f\n", shape.accept(perimeter), shape.accept(area));
        }
    }
}
