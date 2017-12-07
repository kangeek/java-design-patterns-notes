package com.getset.designpatterns.templatemethod;

public class Client {
    public static void main(String[] args) {
        SchoolPerson student = new Student("张三", 12, "光明小学", "山东济南");
        student.selfIntroduction();

        SchoolPerson teacher = new Teacher("李四", 32, "光明小学", "山东青岛");
        teacher.selfIntroduction();
    }
}
