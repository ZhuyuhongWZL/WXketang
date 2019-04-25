package com.example.wxproject.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Lesson {

    @Id
    public Integer lesId;

    public Integer courseId;

    public Date startTime;

    public Date endTime;

    public String lesName;

    public Integer scId;
}
