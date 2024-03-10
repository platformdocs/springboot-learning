package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.User;

@Mapper
public interface UserMapper {
    
    List<User> getAllUsers();

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
