package com.example.demoweb.model;

import java.util.Date;
import java.util.Random;

public class Post {
    private String text;
    private Integer likes;
    private Date creationDate;

    public Post(String text, Date creationDate) {
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
}
