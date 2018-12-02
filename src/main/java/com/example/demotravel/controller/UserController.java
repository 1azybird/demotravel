package com.example.demotravel.controller;

import com.example.demotravel.service.UserService;
import com.example.demotravel.dataobject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/test")
public class UserController {


    @Autowired
    private UserService userService;



    @RequestMapping("getById")
    public User getById() {
        String id="1543393080675";
        User user=userService.getById(id);
       // System.out.println(user.toString());
     return user;
    }

    @RequestMapping("getByProperty")
    public List<User> getByProperty() {
        String property="name";
        String value="sunlin";
        List<User> userList=userService.getByProperty(property,value);
        // System.out.println(user.toString());
        return userList;
    }

    @RequestMapping(value="home")
    public String hello(){
        System.out.println("hello");
        return "hello";
    }


    @RequestMapping(value="save")
    public boolean save(){
        boolean result=userService.save(new User("mmmmmm","hhhh",23));
        return result;
    }


    @RequestMapping(value="delete")
    public void delete(){
        String id="1543468276622";
        userService.delete(id);
    }

    @RequestMapping(value="put")
    public void put(){
        String id="1543468217483";
        userService.put(id,new User("mmmmmm","123",23));
    }
}
