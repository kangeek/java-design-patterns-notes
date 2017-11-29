package com.getset.designpatterns.bridge;

public abstract class Weapon {
    protected Enchantment enchantment;
    abstract void chant();
    abstract void wield();
    abstract void retrieve();
    public void setEnchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
    }
    public Enchantment getEnchantment() {
        return this.enchantment;
    }
}
