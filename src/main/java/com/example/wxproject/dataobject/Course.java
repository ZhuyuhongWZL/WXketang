package com.example.wxproject.dataobject;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "course")
public class Course {

    @Id
    private Integer courseId;

    private String courseName;

    private Integer typeId;

    private String description;

    private Date createTime;

    private String crowd;

    private Integer period;

    @Transient
    private List<Lesson> containLessons;

}
