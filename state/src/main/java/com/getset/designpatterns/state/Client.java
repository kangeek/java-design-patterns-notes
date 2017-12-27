package com.getset.designpatterns.state;

public class Client {
    public static void main(String[] args) {
        Developer developer = new Developer(new EffectiveState());
        developer.develop();
        developer.setState(new ExhaustedState());
        developer.develop();
        developer.setState(new TianRenHeYiState());
        developer.develop();
    }
}
