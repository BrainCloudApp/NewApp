package com.lmq.ui.entity;

import java.io.Serializable;

public class ShareComment implements Serializable {
        public String username;//评论用户名
        public String commentinfo;//评论内容
        public String commenttime;//评论时间

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCommentinfo() {
            return commentinfo;
        }

        public void setCommentinfo(String commentinfo) {
            this.commentinfo = commentinfo;
        }

        public String getCommenttime() {
            return commenttime;
        }

        public void setCommenttime(String commenttime) {
            this.commenttime = commenttime;
        }

}
