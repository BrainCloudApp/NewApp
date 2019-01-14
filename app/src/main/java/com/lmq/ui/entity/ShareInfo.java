package com.lmq.ui.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class ShareInfo implements Serializable{
    public String sharecontent;
    public String shareimgs;
    public int dianzancount;
    public ArrayList<ShareComment> pinglunlist;//评论列表

    public String getpingluninfostr(){
        if(this.pinglunlist==null||this.pinglunlist.size()==0)
            return "";
        String result="";
        for(int i=0;i<this.pinglunlist.size();i++){
            result+=this.pinglunlist.get(i).getUsername()+":"+this.pinglunlist.get(i).getCommentinfo();
            if(i<this.pinglunlist.size()-1)
                result+="\n";
        }

        return result;
    }

    public String getSharecontent() {
        return sharecontent;
    }

    public void setSharecontent(String sharecontent) {
        this.sharecontent = sharecontent;
    }

    public String getShareimgs() {
        return shareimgs;
    }

    public void setShareimgs(String shareimgs) {
        this.shareimgs = shareimgs;
    }

    public int getDianzancount() {

        return dianzancount;
    }

    public void setDianzancount(int dianzancount) {
        this.dianzancount = dianzancount;
    }

    public ArrayList<ShareComment> getPinglunlist() {
        return pinglunlist;
    }

    public void setPinglunlist(ArrayList<ShareComment> pinglunlist) {
        this.pinglunlist = pinglunlist;
    }
}
