package com.example.wxproject.service;

import com.example.wxproject.dataobject.Course;

import java.util.List;

public interface CourseService {

    List<Course> findAll();

    Course findById(Integer courseId);

}
