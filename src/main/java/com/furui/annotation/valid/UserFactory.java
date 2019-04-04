package com.furui.annotation.valid;

import java.lang.reflect.Field;

/**
 * @author furui
 * @date 2019/4/4 0004
 */
public class UserFactory {
    public static boolean check(User user) {
        if (user == null) {
            System.out.println("！！校验对象为空！！");
            return false;
        }

        // 获取User类的所有属性（如果使用getFields，就无法获取到private的属性）
        Field[] fields = User.class.getDeclaredFields();

        for (Field field : fields) {
            // 如果属性有注解，就进行校验
            if (field.isAnnotationPresent(Validate.class)) {
                Validate validate = field.getAnnotation(Validate.class);
                if (field.getName().equals("score")) {
                    if (user.getScore() == null) {
                        if (validate.isNotNull()) {
                            System.out.println("！！分数可空校验不通过：不可为空！！");
                            return false;
                        } else {
                            System.out.println("分数可空校验通过：可以为空");
                            continue;
                        }
                    } else {
                        System.out.println("分数可空校验通过");
                    }

                    if (user.getScore() < validate.min()) {
                        System.out.println("！！分数最小长值校验不通过！！");
                        return false;
                    } else {
                        System.out.println("分数最小长值校验通过");
                    }

                    if (user.getScore() > validate.max()) {
                        System.out.println("！！分数最大长值校验不通过！！");
                        return false;
                    } else {
                        System.out.println("分数最大长值校验通过");
                    }
                }
                if (field.getName().equals("name")) {
                    if (user.getName() == null || user.getName().equals("")) {
                        if (validate.isNotNull()) {
                            System.out.println("！！名字校验不通过：不可为空！！");
                            return false;
                        } else {
                            System.out.println("名字校验通过：可以为空");
                            continue;
                        }
                    } else {
                        System.out.println("名字可空校验通过");
                    }

                    if (user.getName().length() < validate.min()) {
                        System.out.println("！！名字最小长度校验不通过！！");
                        return false;
                    } else {
                        System.out.println("名字最小长度校验通过");
                    }

                    if (user.getName().length() > validate.max()) {
                        System.out.println("！！名字最大长度校验不通过！！");
                        return false;
                    } else {
                        System.out.println("名字最大长度校验通过");
                    }
                }
            }
        }

        return true;
    }
}
