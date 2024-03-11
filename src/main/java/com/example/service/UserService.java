package com.example.service;

import java.util.List;

import com.example.model.User;
import org.springframework.stereotype.Service;

public interface UserService {
    List<User> getAllUsers();

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
