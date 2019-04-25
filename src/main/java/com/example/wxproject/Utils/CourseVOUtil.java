package com.example.wxproject.Utils;

import com.example.wxproject.dataobject.Course;
import com.example.wxproject.dto.AllCourseDTO;

import java.util.List;

public class CourseVOUtil {
    public static AllCourseDTO success(List<Course> list){
        AllCourseDTO result = new AllCourseDTO();
        result.setCode("0");
        result.setCourseList(list);
        result.setMsg("成功");
        return result;
    }

    public static AllCourseDTO nouser(){
        AllCourseDTO result = new AllCourseDTO();
        result.setCode("1");
        result.setCourseList(null);
        result.setMsg("用户openId未绑定");
        return result;
    }

    public static AllCourseDTO error(){
        AllCourseDTO result = new AllCourseDTO();
        result.setCode("-1");
        result.setCourseList(null);
        result.setMsg("失败");
        return result;
    }
}
