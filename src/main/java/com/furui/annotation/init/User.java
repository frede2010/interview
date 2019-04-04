package com.furui.annotation.init;

/**
 * 在数据模型使用注解
 * @author furui
 * @date 2019/4/4 0004
 */

public class User {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    @Init(value="frede")
    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    @Init(value="26")
    public void setAge(String age) {
        this.age = age;
    }
}
