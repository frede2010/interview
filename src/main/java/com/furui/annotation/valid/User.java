package com.furui.annotation.valid;

/**
 * @author furui
 * @date 2019/4/4 0004
 */
public class User {
    @Validate(min = 1, max = 30)
    private String name;

    @Validate(max = 150)
    private Integer score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
