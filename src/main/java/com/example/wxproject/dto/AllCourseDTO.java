package com.example.wxproject.dto;


import com.example.wxproject.dataobject.Course;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)  //json为null的部分不显示
public class AllCourseDTO {
    private String code;
    private String msg;
    private List<Course> courseList;
}
