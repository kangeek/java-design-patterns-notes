package com.getset.designpatterns.composite;

public class File extends Entry {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }

    public void printList(String prefix) {
        System.out.println(prefix + "/" + this);
    }
}
