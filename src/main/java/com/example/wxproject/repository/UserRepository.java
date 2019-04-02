package com.example.wxproject.repository;

import com.example.wxproject.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Integer>{
    User findByOpenId(String openId);
    User findByUsrIdAndUsrName(Integer userId, String userName);

}
