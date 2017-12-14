package com.getset.designpatters.iterator;

public class ArrayList<E> implements Iterable<E> {
    private transient Object[] elements;
    private int capacity = 16;
    private int size;

    public ArrayList(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
        this.size = 0;
    }

    public boolean add(E obj) {
        if (size == capacity) {
            return false;
        } else {
            elements[size++] = obj;
            return true;
        }
    }

    public Iterator<E> iterator() {
        return new Itr<E>();
    }

    private class Itr<E> implements Iterator<E> {

        private int cursor = 0;

        public boolean hasNext() {
            return cursor != size;
        }

        public E next() {
            return (E) elements[cursor++];
        }
    }
}
