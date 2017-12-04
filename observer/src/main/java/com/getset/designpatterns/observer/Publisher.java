package com.getset.designpatterns.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Publisher {
    private List<Subscriber> funs = new ArrayList<Subscriber>();
    private String message;

    public void publishMessage(String message) {
        this.message = message;
        notifyAllSubscribers();
    }

    public String getMessage() {
        return message;
    }

    public void addSubscriber(Subscriber subscriber) {
        funs.add(subscriber);
    }
    public void delSubscriber(Subscriber subscriber) {
        funs.remove(subscriber);
    }
    public void notifyAllSubscribers() {
        Iterator<Subscriber> it = funs.iterator();
        while (it.hasNext()) {
            it.next().update(this);
        }
    }
}
