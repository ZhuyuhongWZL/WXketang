package com.example.wxproject.controller;


import com.example.wxproject.Utils.CourseVOUtil;
import com.example.wxproject.Utils.FindUserUtil;
import com.example.wxproject.dataobject.Course;
import com.example.wxproject.dataobject.Instructor;
import com.example.wxproject.dataobject.Lesson;
import com.example.wxproject.dataobject.User;
import com.example.wxproject.dto.AllCourseDTO;
import com.example.wxproject.repository.InstructorRepository;
import com.example.wxproject.repository.LessonRepository;
import com.example.wxproject.service.impl.CourseServiceImpl;
import com.example.wxproject.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Course")
@Slf4j
public class CourseController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CourseServiceImpl courseService;

    @RequestMapping("/allCourseList")
    public AllCourseDTO AllCourseList(){
        return courseService.AllCourseList();
    }

    //用户课程列表拉取接口
    //http://p2404057t1.wicp.vip/Course/userCourseList?openId=abc1234
    @RequestMapping("/userCourseList")
    public AllCourseDTO UserCourseList(@RequestParam("openId")String openId){
        return courseService.UserCourseList(openId);
    }
}
