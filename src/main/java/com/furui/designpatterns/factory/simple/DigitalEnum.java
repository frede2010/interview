package com.furui.designpatterns.factory.simple;

/**
 * @author furui
 * @date 2019/3/8 0008
 */
public enum DigitalEnum {
    COPMPUTER("1", "电脑"),
    PHONE("2", "手机");
    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    DigitalEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
