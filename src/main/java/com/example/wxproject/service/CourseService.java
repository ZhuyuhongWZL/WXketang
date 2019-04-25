package com.example.wxproject.service;

import com.example.wxproject.dataobject.Course;
import com.example.wxproject.dto.AllCourseDTO;

import java.util.List;

public interface CourseService {

    List<Course> findAll();

    Course findById(Integer courseId);

    AllCourseDTO AllCourseList();

    AllCourseDTO UserCourseList(String openId);

}
