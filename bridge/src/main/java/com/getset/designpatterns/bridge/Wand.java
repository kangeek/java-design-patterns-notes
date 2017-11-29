package com.getset.designpatterns.bridge;

public class Wand extends Weapon {

    public Wand(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    public void chant() {
        System.out.print("牧师拿出魔杖，口中吟唱神圣治疗祷言...");
        enchantment.onActivate();
    }

    public void wield() {
        System.out.print("牧师将魔杖举过头顶挥舞了一下...");
        enchantment.apply();
    }

    public void retrieve() {
        System.out.print("牧师收回魔杖...");
        enchantment.onDeactivate();
    }
}
