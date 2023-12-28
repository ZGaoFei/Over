package com.example.proxylibrary.annotation;

@UserAnnotation(age = 10, name = "lisi")
public class User {
    @UserAgeFiled(10)
    public int age;

    @UserNameFiled("zhangsan")
    public String name;

    @UserMethodAnnotation("age: 20, name: lisi")
    public String getUser() {
        return "";
    }
}
