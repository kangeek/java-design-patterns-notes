package com.getset.designpatterns.mediator;

public class Tester extends TeamMember {

    public Tester(String name) {
        super(name);
        this.role = TeamMember.QA;
    }

    public void dailyWork() {
        System.out.println("我是一名测试，我找出bug，确保代码质量。");
    }

}
