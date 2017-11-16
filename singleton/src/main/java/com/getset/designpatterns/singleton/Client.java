package com.getset.designpatterns.singleton;

public class Client {
    public static void main(String[] args) {
        System.out.println(SingleObject.getInstance());
        System.out.println(SingleObject.getInstance());

        System.out.println(LazySingleObject.getInstance());
        System.out.println(LazySingleObject.getInstance());

        System.out.println(LazyHandlerSingleObject.getInstance());
        System.out.println(LazyHandlerSingleObject.getInstance());

        System.out.println(EnumSingleton.INSTANCE.hashCode());
        System.out.println(EnumSingleton.INSTANCE.hashCode());
    }
}
