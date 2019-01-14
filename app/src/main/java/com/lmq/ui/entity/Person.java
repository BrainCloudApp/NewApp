package com.lmq.ui.entity;

import java.io.Serializable;

public class Person implements Serializable{
    public String name;
    public String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
