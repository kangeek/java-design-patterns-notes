package com.getset.designpatterns.mediator;

public abstract class TeamMember {
    // 团队角色
    public static final String RD = "开发人员";
    public static final String QA = "测试人员";
    public static final String OP = "运维人员";

    // 仅与自己的组长维护引用关系
    private TechLeader techLeader;
    private String name;
    protected String role;

    public TeamMember(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTechLeader(TechLeader techLeader) {
        this.techLeader = techLeader;
    }

    // 向组长（调停者/中心角色）发送信息
    public void reportToLeader(String message) {
        techLeader.memberReport(this, message);
    }

    // 收到来自组长（调停者/中心角色）的消息
    public void tempTask(String task) {
        System.out.println("[" + role + "]" + name + "收到来自组长的安排： "  + task);
    }

    // 无伤大雅的方法，与模式无关
    public abstract void dailyWork();
}
