package com.getset.designpatterns.composite;

public abstract class Entry {
    public abstract String getName();
    public abstract int getSize();
    public Entry add(Entry entry) {
        throw new RuntimeException();
    }
    public void printList() {
        printList("");
    }
    public abstract void printList(String prefix);

    @Override
    public String toString() {
        return getName() + "(" + getSize() + ")";
    }
}
