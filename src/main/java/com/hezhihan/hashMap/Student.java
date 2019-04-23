package com.hezhihan.hashMap;

/**
 * @author zhihan.he
 * @version V1.0
 * @Title Student
 * @Descripion TODO
 * @date 2019/4/22 16:20
 */
public class Student {
    private String name;

    private int age;

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

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + name.hashCode();
        result = result * 31 + age;

        return result;
    }

    @Override
    public boolean equals(Object other) {
        System.out.println("equals method invoked!");

        if(other == this)
            return true;
        if(!(other instanceof Student))
            return false;

        Student o = (Student)other;
        return o.name.equals(name) && o.age == age;
    }

}
