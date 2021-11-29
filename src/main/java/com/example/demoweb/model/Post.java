package com.example.demoweb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

@Entity
public class Post implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private Integer likes;
    private Date creationDate;

    public Post() {
    }

    public Post(Long id, String text, Date creationDate) {
        this.id = id;
        this.text = text;
        int max = 500;
        this.likes = 0;
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

    public Long getId() {
        return id;
    }
}
