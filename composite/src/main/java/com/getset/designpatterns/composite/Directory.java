package com.getset.designpatterns.composite;

import com.sun.xml.internal.ws.util.QNameMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Directory extends Entry {
    private String name;
    private List<Entry> items = new ArrayList<Entry>();

    public Directory(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        int size = 0;
        Iterator it = this.items.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            size += entry.getSize();
        }
        return size;
    }

    public Entry add(Entry entry) {
        items.add(entry);
        return this;
    }

    public void printList(String prefix) {
        System.out.println(prefix + "/" + getName());
        Iterator it = items.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            entry.printList(prefix + "/" + this.name);
        }
    }
}
