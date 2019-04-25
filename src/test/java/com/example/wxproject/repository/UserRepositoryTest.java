package com.example.wxproject.repository;

import com.example.wxproject.dataobject.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void findOneTest(){
        User user = repository.findByOpenId("abc123");
        System.out.println(user.getUserName());
    }

    @Test
    public void findByUsrNameAndUsrId(){
        User user = repository.findByUsrIdAndUsrName(1,"朱禹宏");
        System.out.println(user.getUserName());

    }

    @Test
    public void save(){
        User user = new User(2,"科比","bba",2);
        user.setPhone("13999999999");
        User result = repository.save(user);
    }


}