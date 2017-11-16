package com.getset.designpatterns.singleton;

public class LazyHandlerSingleObject {
    private static class SingleObjectHandler {
        private final static LazyHandlerSingleObject instance = new LazyHandlerSingleObject();
    }
    private static LazyHandlerSingleObject instance;

    public static LazyHandlerSingleObject getInstance() {
        return SingleObjectHandler.instance;
    }
}
