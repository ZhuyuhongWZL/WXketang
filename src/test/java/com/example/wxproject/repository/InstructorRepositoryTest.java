package com.example.wxproject.repository;

import com.example.wxproject.dataobject.Instructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

//上面的注解不要少！！！
@RunWith(SpringRunner.class)
@SpringBootTest
public class InstructorRepositoryTest {

    @Autowired
    private InstructorRepository repository;

    @Test
    public void findByTeaUsrId() {
        List<Instructor> result = repository.findByTeaUsrId(1234);
        for (Instructor instructor: result){
            System.out.println(instructor.teaCourseId);
        }
    }
}