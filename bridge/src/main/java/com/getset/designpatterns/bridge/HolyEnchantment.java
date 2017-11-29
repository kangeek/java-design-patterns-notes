package com.getset.designpatterns.bridge;

public class HolyEnchantment implements Enchantment {
    public void onActivate() {
        System.out.println("武器逐渐泛起金黄色的圣光...");
    }

    public void apply() {
        System.out.println("一道金黄色的圣光从武器发出，传到队友身上，队友加血1000");
    }

    public void onDeactivate() {
        System.out.println("武器的光芒迅速消失...");
    }

    @Override
    public String toString() {
        return "神圣魔法";
    }
}
