package com.example.demoweb.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyDateProvider implements DateProvider{
    @Override
    public Date getDate() {
        return new Date();
    }
}
