package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.alibaba.fastjson.*;
import com.example.model.User;
import com.example.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.starter.redis.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TestController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserService userService;
    
    @GetMapping("/test")
    public String test() {
        redisTemplate.opsForValue().set("test", "test1111");
        System.out.println(redisTemplate.opsForValue().get("test"));
        return "Hello, World!";
    }

    @GetMapping("/users")
    public String getUsers() {
        StringBuffer sb = new StringBuffer();
        List<User> users = userService.getAllUsers();
        for(User user : users) {
            sb.append(user.getUsername() + "," + user.getPassword() + "<br/>");
        }
        return sb.toString();
    }
    
}
