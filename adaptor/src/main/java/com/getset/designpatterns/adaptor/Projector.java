package com.getset.designpatterns.adaptor;

public class Projector {
    public static void main(String[] args) {
        // 借新电脑
        AdvancedComputer advancedComputer = new AdvancedComputer();
        System.out.println(advancedComputer.transmitWithHDMI());
        System.out.println(advancedComputer.transmitWithVGA());

        // 用转接头
        Computer computer = new Computer();
        DisplayAdaptor adapter = new DisplayAdaptor(computer);
        System.out.println(adapter.transmitWithHDMI());
        System.out.println(adapter.transmitWithVGA());
    }
}
