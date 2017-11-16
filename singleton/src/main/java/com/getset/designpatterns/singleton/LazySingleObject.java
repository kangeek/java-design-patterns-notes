package com.getset.designpatterns.singleton;

public class LazySingleObject {
    private static volatile LazySingleObject instance;
    private LazySingleObject() {}
    public static LazySingleObject getInstance() {
        if (instance == null) {
            synchronized (LazySingleObject.class) {
                if (instance == null) {
                    instance = new LazySingleObject();
                }
            }
        }
        return instance;
    }
}
