package com.getset.designpatterns.templatemethod;

public abstract class SchoolPerson {
    protected String name;
    protected int age;
    protected String schoolName;
    protected String hometown;

    public SchoolPerson(String name, int age, String schoolName, String hometown) {
        this.name = name;
        this.age = age;
        this.schoolName = schoolName;
        this.hometown = hometown;
    }

    public void selfIntroduction() {
        myBasicInfo();
        mySlogan();
    }

    public abstract void myBasicInfo();

    public abstract void mySlogan();
}
