package com.example.wxproject.controller;


import com.example.wxproject.dataobject.User;
import com.example.wxproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TXCloud")
public class TXCloudTest {

    @Autowired
    private UserRepository repository;

    @RequestMapping("/test")
    public String Test(){
        return "You have successfully connected to TXCloud Server";
    }

    @RequestMapping("/usertest")
    public String UserTest(){
        User user = repository.findByOpenId("abc123");
        return user.getUserName();
    }
}
