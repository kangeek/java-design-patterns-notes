package com.getset.designpatterns.command;

import java.util.ArrayList;
import java.util.List;

public class DrawingApp {
    private List<Command> commands = new ArrayList<Command>();
    public void takeCommand(Command command) {
        commands.add(command);
    }
    public void CommandsDone() {
        for (Command command:commands) {
            command.doCmd();
        }
    }
    public void undoLastCommand() {
        commands.get(commands.size() - 1).undoCmd();
    }
}
