package com.example.wxproject.repository;

import com.example.wxproject.dataobject.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Integer>{

    List<Instructor> findByTeaUsrId(Integer teaUsrId);
}
