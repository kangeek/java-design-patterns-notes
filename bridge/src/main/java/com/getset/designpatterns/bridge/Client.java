package com.getset.designpatterns.bridge;

public class Client {
    public static void main(String[] args) {
        System.out.println("进入副本，使用神圣天赋 >>>");
        Enchantment enchantment = new HolyEnchantment();
        Wand wand = new Wand(enchantment);
        wand.chant();
        wand.wield();
        wand.retrieve();

        System.out.println("野外打怪，使用暗影天赋 >>>");
        enchantment = new ShadowEnchantment();
        Hammer hammer = new Hammer(enchantment);
        hammer.chant();
        hammer.wield();
        hammer.retrieve();
    }
}
