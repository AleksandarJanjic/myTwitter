package com.janja.myTwitter.beans;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Like {

    private int messageid;

    private int userid;

    private Timestamp likeDate;

    public int getMessageid() {
        return messageid;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Timestamp getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Timestamp likeDate) {
        this.likeDate = likeDate;
    }
}
