package com.example.wxproject.service.impl;

import com.example.wxproject.Utils.CourseVOUtil;
import com.example.wxproject.dataobject.Course;
import com.example.wxproject.dataobject.Instructor;
import com.example.wxproject.dataobject.Lesson;
import com.example.wxproject.dataobject.User;
import com.example.wxproject.dto.AllCourseDTO;
import com.example.wxproject.repository.CourseRepository;
import com.example.wxproject.repository.InstructorRepository;
import com.example.wxproject.repository.LessonRepository;
import com.example.wxproject.repository.UserRepository;
import com.example.wxproject.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Integer courseId) {
        return courseRepository.findById(courseId).get();
    }

    @Override
    public AllCourseDTO AllCourseList(){
        List<Course> list =  findAll();
        return CourseVOUtil.success(list);
    }

    @Override
    public AllCourseDTO UserCourseList(String openId){
        List<Instructor> instructors ;
        List<Course> result = new ArrayList<>();
        Integer userId;
        User user = userRepository.findByOpenId(openId);
        if (user == null){
            return CourseVOUtil.nouser();
        }
        userId = user.getUserId();
        switch (user.getRoleId()){
            case 1://学生身份
                break;
            case 2://教师身份
                instructors = instructorRepository.findByTeaUsrId(userId);
                for (Instructor instructor: instructors){
                    Course course = findById(instructor.getTeaCourseId());
                    List<Lesson> lessons = lessonRepository.findByCourseIdAndScId(instructor.getTeaCourseId(), instructor.getScId());
                    course.setContainLessons(lessons);
                    result.add(course);
                }
                break;
            case 3://学校身份
                break;

        }
        log.info("size = " + result.size());
        return CourseVOUtil.success(result);
    }

}
