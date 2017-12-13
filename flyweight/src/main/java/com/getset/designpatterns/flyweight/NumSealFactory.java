package com.getset.designpatterns.flyweight;

import java.util.HashMap;
import java.util.Map;

public class NumSealFactory {
    private Map<Integer, NumSeal> seals = new HashMap<Integer, NumSeal>(10);
    public NumSeal getSeal(Integer num) {
        NumSeal seal = seals.get(num);
        if (seal == null) {
            seal = new NumSeal(num);
            System.out.println("制作一个数字为" + num + "的印章");
            seals.put(num, seal);
            return seal;
        }
        return seal;
    }
}
