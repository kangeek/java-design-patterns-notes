package com.getset.designpatterns.decorator;

public class Client {
    public static void main(String[] args) {
        Display d1 = new StringDisplay("Hello, world!");
        Display d2 = new SideBoardStringDisplay(d1);
        Display d3 = new FullBoardStringDisplay(new SideBoardStringDisplay(new FullBoardStringDisplay(d2)));
        System.out.println("显示字符串>>>>>>");
        d1.show();
        System.out.println("\n增加两侧边框>>>>>>");
        d2.show();
        System.out.println("\n再增加全边框、两侧边框、全边框>>>>>>");
        d3.show();
    }
}
