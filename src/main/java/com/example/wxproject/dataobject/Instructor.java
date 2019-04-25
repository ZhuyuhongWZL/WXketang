package com.example.wxproject.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
//教师教课表
public class Instructor {

    @Id
    public Integer teaCourseId;

    public Integer teaUsrId;

    public Integer scId;
}
