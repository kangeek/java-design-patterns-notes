package com.getset.designpatterns.adaptor;

public class DisplayAdaptor implements DisplayRequire {
    private Computer computer;

    public DisplayAdaptor(Computer computer) {
        this.computer = computer;
    }

    public String transmitWithVGA() {
        return this.computer.transmitWithVGA();
    }

    public String transmitWithHDMI() {
        return this.computer.transmitWithVGA();
    }
}
