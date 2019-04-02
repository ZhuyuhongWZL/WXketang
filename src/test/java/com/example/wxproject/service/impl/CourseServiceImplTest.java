package com.example.wxproject.service.impl;

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
public class CourseServiceImplTest {

    @Autowired
    private CourseServiceImpl courseService;

    @Test
    public void findAll() {
        List<Course> list = courseService.findAll();
        for (Course course:list){
            System.out.println(course.getCourseName());
        }
    }

    @Test
    public void findByCourseId() {
    }
}