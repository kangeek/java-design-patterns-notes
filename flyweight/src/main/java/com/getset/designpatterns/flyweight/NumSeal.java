package com.getset.designpatterns.flyweight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumSeal {
    private Integer num;

    private String[] fontLines;

    // 构造方法中读取字体信息
    public NumSeal(Integer num) {
        this.num = num;
        this.fontLines = new String[10];
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/fonts/seal-" + num + ".txt")));
            String line;
            for (int i = 0; i < 10; i++) {
                line = reader.readLine();
                if (line != null) {
                    fontLines[i] = line;
                } else {
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 按行打印，共10行10列靠左打印
    public void sealPrint(int line) {
        System.out.printf(" %-10.10s", fontLines[line]);
    }
}
