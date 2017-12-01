package com.getset.designpatterns.decorator;

public class FullBoardStringDisplay extends BoardStringDisplay {

    protected FullBoardStringDisplay(Display display) {
        super(display);
    }

    public int getColumns() {
        return 1 + display.getColumns() + 1;
    }

    public int getRows() {
        return 1 + display.getRows() + 1;
    }

    public String getRowText(int row) {
        if (row == 0 || row == display.getRows() + 1) {
            return "+" + makeLine('-', display.getColumns()) + "+";
        } else  {
            return "|" + display.getRowText(row - 1) + "|";
        }
    }

    private String makeLine(char ch, int count) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < count; i++) {
            buf.append(ch);
        }
        return buf.toString();
    }
}
