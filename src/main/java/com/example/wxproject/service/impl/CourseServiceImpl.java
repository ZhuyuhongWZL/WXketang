package com.example.wxproject.service.impl;

import com.example.wxproject.dataobject.Course;
import com.example.wxproject.repository.CourseRepository;
import com.example.wxproject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Integer courseId) {
        return courseRepository.findById(courseId).get();
    }
}
