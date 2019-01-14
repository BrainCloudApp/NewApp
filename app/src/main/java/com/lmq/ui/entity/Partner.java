package com.lmq.ui.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/12/28 0028.
 * 相同病情的患者信息
 */

public class Partner implements Serializable{
    String name;//姓名
    String id;//用户id
    String healthinfo;//健康情况
    String headimg;//头像
    String practicelist;//训练项目情况

    ShareInfo shareinfo;//康复心得情况
    public void Partner(String name,String id,String healthinfo,String headimg,String practicelist,ShareInfo shareInfo){
        this.name=name;
        this.id=id;
        this.headimg=headimg;
        this.healthinfo=healthinfo;
        this.practicelist=practicelist;
        this.shareinfo=shareInfo;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHealthinfo() {
        return healthinfo;
    }

    public void setHealthinfo(String healthinfo) {
        this.healthinfo = healthinfo;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getPracticelist() {
        return practicelist;
    }

    public void setPracticelist(String practicelist) {
        this.practicelist = practicelist;
    }

    public ShareInfo getShareinfo() {
        return shareinfo;
    }

    public void setShareinfo(ShareInfo shareinfo) {
        this.shareinfo = shareinfo;
    }

}
