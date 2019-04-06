package com.furui.annotation.init;

import com.alibaba.fastjson.JSONObject;

/**
 * @author furui
 * @date 2019/4/4 0004
 */
public class Client {
    public static void main(String[] args) {
        User u = UserFactory.create();
        System.out.println(JSONObject.toJSONString(u));
    }
}
