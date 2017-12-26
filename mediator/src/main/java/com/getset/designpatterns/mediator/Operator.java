package com.getset.designpatterns.mediator;

public class Operator extends TeamMember {

    public Operator(String name) {
        super(name);
        this.role = TeamMember.OP;
    }

    public void dailyWork() {
        System.out.println("我是一个运维，保证系统稳定运行，如果有线上bug及时回滚，话说开发人员写的程序真不稳定。");
    }

}
