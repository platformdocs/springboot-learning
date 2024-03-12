package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.model.Users;
import com.example.service.UsersService;

public class UsersServiceImpl implements UsersService {

    @Autowired
    private MongoTemplate MongoTemplate;

    @Override
    public void saveUser(Users users) {
        MongoTemplate.save(users)
    }

    @Override
    public List<Users> getAllUsers() {
        return MongoTemplate.findAll(Users.class);
    }
}
