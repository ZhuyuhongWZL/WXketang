package com.example.wxproject.Utils;

import com.example.wxproject.dataobject.User;
import com.example.wxproject.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;


public class FindUserUtil {

    private static UserServiceImpl userService =  new UserServiceImpl();

    //存储用户信息实体类缓存
    public static LruCache<String, User> userLruCache = new LruCache<>(50);


}
