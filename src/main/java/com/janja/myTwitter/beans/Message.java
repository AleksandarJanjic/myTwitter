package com.janja.myTwitter.beans;

import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

@Component
public class Message {

    private String content;

    private String username;

    private int author;

    private int messageid;

    private Timestamp messageTime;

    private Long totalLikes;

    private ArrayList<User> usersLiked;

    public Message(String content, int author, Timestamp messageTime) {
        this.content = content;
        this.author = author;
        this.messageTime = messageTime;
    }

    public Message() {

    }

    public Message(String content, int author, Timestamp messageTime, Long totalLikes) {
        this.content = content;
        this.author = author;
        this.messageTime = messageTime;
        this.totalLikes = totalLikes;
    }

    public Message(String content, int author, Timestamp messageTime, Long totalLikes, ArrayList<User> usersLiked) {
        this.content = content;
        this.author = author;
        this.messageTime = messageTime;
        this.totalLikes = totalLikes;
        this.usersLiked = usersLiked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getMessageid() {
        return messageid;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    public Long getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Long totalLikes) {
        this.totalLikes = totalLikes;
    }

    public ArrayList<User> getUsersLiked() {
        return usersLiked;
    }

    public void setUsersLiked(ArrayList<User> usersLiked) {
        this.usersLiked = usersLiked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Message> messagesListFactory() {
        return new ArrayList<Message>();
    }
}
