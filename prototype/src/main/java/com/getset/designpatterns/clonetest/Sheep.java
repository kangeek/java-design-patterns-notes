package com.getset.designpatterns.clonetest;

import java.io.*;

public class Sheep implements Cloneable, Serializable {
    private String name;
    private int age;
    private String breed;
    private EarTag earTag;

    public Sheep(String name, int age, String breed, EarTag earTag) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.earTag = earTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public EarTag getEarTag() {
        return earTag;
    }

    public void setEarTag(EarTag earTag) {
        this.earTag = earTag;
    }

    @Override
    public String toString() {
        return this.name + "是一只" + this.age + "岁的" + this.breed + ", 它的" + this.earTag.getColor() + "色耳牌上写着" + this.earTag.getId() + "号。";
    }

    @Override
    public Sheep clone() throws CloneNotSupportedException {
        return (Sheep) super.clone();
    }

    public Sheep deepClone() throws CloneNotSupportedException {
        Sheep s = (Sheep)super.clone();
        s.setEarTag(s.getEarTag().clone());
        return s;
    }

    public Sheep serializedClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bao);
        oo.writeObject(this);
        ByteArrayInputStream bai = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bai);
        return (Sheep) oi.readObject();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Sheep))
            return false;
        Sheep s = (Sheep) obj;
        return s.name.equals(this.name) &&
                s.age == this.age &&
                s.breed.equals(this.breed) &&
                s.earTag.equals(this.earTag);
    }
}
