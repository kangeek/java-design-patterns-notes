package com.getset.designpatterns.decorator;

public class SideBoardStringDisplay extends BoardStringDisplay {

    protected SideBoardStringDisplay(Display display) {
        super(display);
    }

    public int getColumns() {
        return 1 + display.getColumns() + 1;    // 文字两侧各增加一个字符
    }

    public int getRows() {
        return display.getRows();               // 行数不变
    }

    public String getRowText(int row) {
        return "|" + display.getRowText(row) + "|";
    }
}
