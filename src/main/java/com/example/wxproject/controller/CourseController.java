package com.example.wxproject.controller;


import com.example.wxproject.Utils.CourseVOUtil;
import com.example.wxproject.dataobject.Course;
import com.example.wxproject.dto.AllCourseDTO;
import com.example.wxproject.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Course")
public class CourseController {

    @Autowired
    private CourseServiceImpl service;

    @RequestMapping("/allCourseList")
    public AllCourseDTO AllCourseList(){
        List<Course> list =  service.findAll();
        return CourseVOUtil.success(list);
    }
}
