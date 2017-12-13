package com.getset.designpatterns.flyweight;

public class Client {
    public static void main(String[] args) {
        new NumsPrinter("18610861680").print();

        // String的字符串池
        String a = "abc";
        String b = "abc";
        String c = new String("abc");
        System.out.println(a == b);
        System.out.println(c == b);
        // Integer中缓存的使用
        System.out.println(Integer.valueOf(123) == Integer.valueOf(123));
        System.out.println(Integer.valueOf(1234) == Integer.valueOf(1234));
    }
}
