package com.example.demoweb.model;

import java.util.Date;
import java.util.Random;

public class Post {
    private long id;
    private String text;
    private Integer likes;
    private Date creationDate;

    public Post(long id, String text, Date creationDate) {
        this.id = id;
        this.text = text;
        int max = 500;
        this.likes = (int) (Math.random() * ++max);
        this.creationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public Integer getLikes() {
        return likes;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setLikes(Integer likes)
    {
        this.likes = likes;
    }

    public long getId() {
        return id;
    }
}
