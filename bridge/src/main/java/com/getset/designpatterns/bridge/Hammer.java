package com.getset.designpatterns.bridge;

public class Hammer extends Weapon {

    public Hammer(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    public void chant() {
        System.out.print("牧师拿出锤子，口中吟唱神圣治疗祷言...");
        enchantment.onActivate();
    }

    public void wield() {
        System.out.print("牧师将锤子举过头顶挥舞了一下...");
        enchantment.apply();
    }

    public void retrieve() {
        System.out.print("牧师收回锤子...");
        enchantment.onDeactivate();
    }
}
