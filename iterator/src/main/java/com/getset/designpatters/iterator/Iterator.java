package com.getset.designpatters.iterator;

public interface Iterator<T> {
    boolean hasNext();
    T next();
}
