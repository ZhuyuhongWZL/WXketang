package com.example.wxproject.service.impl;

import com.example.wxproject.dataobject.User;
import com.example.wxproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.wxproject.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    public User findOne(String openId) {
        return repository.findByOpenId(openId);
    }

    @Override
    public User findByUserIdAndUserName(Integer userId, String userName) {
        return repository.findByUsrIdAndUsrName(userId, userName);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}
