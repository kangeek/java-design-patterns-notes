package com.getset.designpatterns.mediator;

public class Developer extends TeamMember {

    public Developer(String name) {
        super(name);
        this.role = TeamMember.RD;
    }

    public void dailyWork() {
        System.out.println("我是一个码农，我经常加班写代码，困了累了可能写出bug来。");
    }

}
