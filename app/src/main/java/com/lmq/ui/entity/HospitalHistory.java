package com.lmq.ui.entity;

import java.io.Serializable;

public class HospitalHistory implements Serializable{
    public String intime;
    public String outtime;
    public String doctor;
    public String chufang;
    public String id;//id
    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getChufang() {
        return chufang;
    }

    public void setChufang(String chufang) {
        this.chufang = chufang;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
