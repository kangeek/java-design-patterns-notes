package com.getset.designpatterns.adaptor;

public class AdvancedComputer extends Computer implements DisplayRequire {
    public String transmitWithHDMI() {
        return this.ppt;
    }
}
