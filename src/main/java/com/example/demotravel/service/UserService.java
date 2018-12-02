package com.example.demotravel.service;

import com.example.demotravel.dataobject.User;

import java.util.List;

public interface UserService {
    public User getById(String id);
    public List<User> getByProperty(String property, String value);
    public boolean save(User user);
    public void delete(String id);
    public void put(String id,User user);
}
