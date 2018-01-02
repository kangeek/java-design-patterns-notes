package com.getset.designpatterns.memento;

import java.util.Stack;

public class User {
    public static void main(String[] args) {
        Stack<Snapshot> snapshots = new Stack<Snapshot>();

        VirtualMachine ubuntu = new VirtualMachine("ubuntu", "1个4核CPU，8G内存，80G硬盘");

        ubuntu.startup();
        ubuntu.openApp("网易云音乐");
        ubuntu.openApp("谷歌浏览器");
        ubuntu.saveFile("/tmp/test.txt");
        System.out.println(ubuntu);

        snapshots.push(ubuntu.takeSnapshot());

        ubuntu.closeApp("网易云音乐");
        ubuntu.openApp("IntelliJ IDEA");
        ubuntu.delFile("/tmp/test.txt");
        ubuntu.saveFile("/workspace/hello.java");
        System.out.println(ubuntu);

        ubuntu.restoreSnapshot(snapshots.peek());
        System.out.println("恢复到最近的快照...");

        System.out.println(ubuntu);
    }
}
