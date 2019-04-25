package com.example.wxproject.repository;

import com.example.wxproject.dataobject.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    List<Lesson> findByCourseIdAndScId(Integer courseId, Integer scId);


}
