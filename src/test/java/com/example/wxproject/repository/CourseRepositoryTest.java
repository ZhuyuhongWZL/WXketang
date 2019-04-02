package com.example.wxproject.repository;

import com.example.wxproject.dataobject.Course;
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
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    public void findAll(){
        List<Course> list = repository.findAll();
        System.out.println(list.size());
    }

    @Test
    public void findById() {
        Course course = repository.findById(10001).get();
        System.out.println(course.getCourseName());
    }
}