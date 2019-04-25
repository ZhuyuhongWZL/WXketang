package com.example.wxproject.repository;

import com.example.wxproject.dataobject.Lesson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LessonRepositoryTest {

    @Autowired
    private LessonRepository repository;

    @Test
    public void findByCourseId() {

        List<Lesson> result = repository.findByCourseIdAndScId(10001, 1);
        for (Lesson lesson: result){
            System.out.println(lesson.lesName);
        }
    }
}