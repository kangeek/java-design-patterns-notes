package com.getset.designpatterns.clonetest;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {

        // 克隆羊多利例子的测试
        Sheep jane = new Sheep("简", 5, "多塞特白面绵羊", new EarTag(12345, "黄色"));
        System.out.println(jane);
//        Sheep dolly = jane.clone();           // 浅复制
//        Sheep dolly = jane.deepClone();       // 深复制
        Sheep dolly = jane.serializedClone();   // 基于序列化进行深复制
        System.out.println("克隆后...");
        System.out.println(jane.equals(dolly));
        dolly.setName("多利");
        dolly.setAge(6);
        dolly.getEarTag().setId(12346);
        System.out.println(dolly);
        System.out.println(jane);
        System.out.println(jane.getEarTag() == dolly.getEarTag());
        System.out.println(jane.equals(dolly));

        // 继承关系clone的例子
        ClassB b = new ClassB();
        b.setA(1);
        b.setB("b");
        ClassB b1 = (ClassB) b.clone();
        System.out.println(b1.getB());
    }

}
