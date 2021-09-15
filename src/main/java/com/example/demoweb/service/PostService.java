package com.example.demoweb.service;

import com.example.demoweb.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    public List<Post> posts = new ArrayList<>();

    public PostService() {
        posts.add(new Post("Сентябрь горит", new Date(4567899654326L)));
        posts.add(new Post("Здесь могла быть ваша реклама", new Date(445687773555L)));
        posts.add(new Post("17", new Date(234566534458L)));
    }

    public void create(String text) {
        posts.add(new Post(text, new Date()));
    }

    public List<Post> listAllPosts(){
        return posts;
    }
}
