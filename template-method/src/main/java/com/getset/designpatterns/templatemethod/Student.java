package com.getset.designpatterns.templatemethod;

public class Student extends SchoolPerson {
    public Student(String name, int age, String schoolName, String hometown) {
        super(name, age, schoolName, hometown);
    }

    public void myBasicInfo() {
        System.out.println("我是一名学生，名叫" + this.name + "， 今年" + this.age + "岁， " + this.hometown + "人， 在" + this.schoolName + "上学。");
    }

    public void mySlogan() {
        System.out.println("在我在" + this.schoolName + "求学的过程中，我一定 好好学习，天天向上！");
    }
}
