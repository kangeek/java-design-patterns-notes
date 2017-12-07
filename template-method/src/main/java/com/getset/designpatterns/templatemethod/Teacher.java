package com.getset.designpatterns.templatemethod;

public class Teacher extends SchoolPerson {

    public Teacher(String name, int age, String schoolName, String hometown) {
        super(name, age, schoolName, hometown);
    }

    public void myBasicInfo() {
        System.out.println("我是一名教师，名叫" + this.name + "， 今年" + this.age + "岁， " + this.hometown + "人， 在" + this.schoolName + "教书。");
    }

    public void mySlogan() {
        System.out.println("在我在" + this.schoolName + "教学的过程中，我一定 为人师表，诲人不倦！");
    }
}
