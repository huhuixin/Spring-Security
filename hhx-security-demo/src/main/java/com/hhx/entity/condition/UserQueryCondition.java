package com.hhx.entity.condition;

import java.util.Date;

public class UserQueryCondition{

    private String username;
    private Date age;
    private Date ageTo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public Date getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(Date ageTo) {
        this.ageTo = ageTo;
    }
}
