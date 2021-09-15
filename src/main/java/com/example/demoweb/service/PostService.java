package com.example.demoweb.service;

import com.example.demoweb.model.Post;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PostService {

    public List<Post> listAllPosts(){
        return Arrays.asList(
                new Post("Сентябрь горит", 11),
                new Post("Здесь могла быть ваша реклама", 333),
                new Post("17", 987)
        );
    }
}
