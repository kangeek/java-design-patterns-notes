package com.getset.designpatterns.mediator;

import java.util.ArrayList;
import java.util.List;

public class TechLeader {
    // 维护有各个组员的引用
    private List<TeamMember> members;

    public TechLeader() {
        members = new ArrayList<TeamMember>();
    }

    public void addTeamMember(TeamMember teamMember) {
        members.add(teamMember);
        teamMember.setTechLeader(this);
    }

    public void memberReport(TeamMember reporter, String message) {
        if (message.contains("请假")) {
            reporter.tempTask("同意！");
            // 对相关人员发送消息或安排其执行操作
            for (TeamMember m : members) {
                if (m.getName().equals(reporter.getName())) {
                    continue;
                } else if (m.role.equals(TeamMember.RD)) {
                    m.tempTask(reporter.getName() + "请假了，期间请接手他的开发工作。");
                } else if (m.role.equals(TeamMember.QA)) {
                    m.tempTask(reporter.getName() + "请假了，期间请将他的bug交由其他开发人员处理。");
                } else if (m.role.equals(TeamMember.OP)) {
                    m.tempTask(reporter.getName() + "请假了，期间请将他的线上问题交由其他开发人员处理。");
                }
            }
        } else if (message.contains("建议")) {

        } else if (message.contains("技术分享")) {

        }
    }
}
