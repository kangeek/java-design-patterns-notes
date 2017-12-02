package com.getset.designpatterns.composite;

import javax.swing.text.html.parser.Entity;

public class Client {
    public static void main(String[] args) {
        Entry bindir = new Directory("bin");
        Entry usrdir = new Directory("usr");
        Entry tmpdir = new Directory("lib");

        bindir
                .add(new File("bash", 4))
                .add(new File("ls", 6))
                .add(new File("ip", 8));

        usrdir
                .add(new Directory("bin")
                    .add(new File("top", 10))
                    .add(new File("ssh", 12)))
                .add(new Directory("local")
                    .add(new Directory("bin")
                        .add(new File("eclipse", 4))
                        .add(new File("idea", 4)))
                    .add(new Directory("src")));

        tmpdir
                .add(new File("test.txt", 12));

        Entry rootdir = new Directory("root");
        rootdir.add(bindir).add(usrdir).add(tmpdir);
        rootdir.printList();

    }
}
