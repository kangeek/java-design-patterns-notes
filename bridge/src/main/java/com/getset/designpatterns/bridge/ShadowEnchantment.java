package com.getset.designpatterns.bridge;

public class ShadowEnchantment implements Enchantment {
    public void onActivate() {
        System.out.println("武器逐渐泛起黑紫色的暗影...");
    }

    public void apply() {
        System.out.println("一道黑紫色的暗影从武器发出，传到敌人身上，敌人掉血2000");
    }

    public void onDeactivate() {
        System.out.println("武器的暗影迅速消失...");
    }

    @Override
    public String toString() {
        return "暗影魔法";
    }
}
