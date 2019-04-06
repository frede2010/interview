package com.furui.annotation.valid;

/**
 * @author furui
 * @date 2019/4/4 0004
 */
public class Client {
    public static void main(String[] args) {
        User u = new User();
        u.setName("frede");
        u.setScore(0);

        System.out.println(UserFactory.check(u));
    }
}
