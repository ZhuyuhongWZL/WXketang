package com.example.wxproject.service;

import com.example.wxproject.dataobject.User;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    User findByOpenId(String openId);

    User findByUserIdAndUserName(Integer userId, String userName);

    User save(User user);

}
