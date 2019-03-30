package com.example.wxproject.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TXCloud")
public class TXCloudTest {
    @RequestMapping("/test")
    public String Test(){
        return "You have successfully connected to TXCloud Server";
    }
}
