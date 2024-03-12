package com.example.service;

import java.util.List;

import com.example.model.Users;

public interface UsersService {

    void saveUser();

    List<Users> getAllUsers();
} 
