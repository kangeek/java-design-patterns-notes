package com.getset.designpatterns.composite;

public class EntryAddException extends RuntimeException {
    public EntryAddException() {
    }

    public EntryAddException(String message) {
        super(message);
    }
}
