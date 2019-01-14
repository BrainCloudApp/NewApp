package com.lmq.ui.entity;

import java.io.Serializable;

public class Health_Base implements Serializable{
    public String id;//id
    public String name;//姓名
    public String img;//头像
    public String sex;//性别
    public String age;//年龄
    public String healthstatus;//健康状况
    public String healthproblem;//健康问题
    public String phone;//手机号
    public String height;//身高
    public String weight;//体重

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHealthstatus() {
        return healthstatus;
    }

    public void setHealthstatus(String healthstatus) {
        this.healthstatus = healthstatus;
    }

    public String getHealthproblem() {
        return healthproblem;
    }

    public void setHealthproblem(String healthproblem) {
        this.healthproblem = healthproblem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
