package com.getset.designpatterns.state;

public class Developer {
    private State state;

    public Developer(State state) {
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void develop() {
        state.coding();
    }
}
