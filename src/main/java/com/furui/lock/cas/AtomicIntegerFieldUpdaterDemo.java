package com.furui.lock.cas;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 原子更新整数型字段 必须int，不能为Integer
 * @author furui
 * @date 2019/4/12 0012
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static AtomicIntegerFieldUpdater<User> atomic =
            AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
//    public static AtomicReferenceFieldUpdater atomicReferenceField = AtomicReferenceFieldUpdater.newUpdater(User.class, String.class, "userName");
    public static void main(String[] args) {
        User user = new User("xuliugen", 24);
        System.out.println(atomic.getAndIncrement(user));
        System.out.println(atomic.get(user));

        System.out.println();
    }

    static class User {
        volatile String userName;
        volatile int age;

        public User(String userName, int age) {
            this.userName = userName;
            this.age = age;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }
}
