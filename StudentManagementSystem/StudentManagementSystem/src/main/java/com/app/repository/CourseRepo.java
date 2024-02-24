package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Course;

public interface CourseRepo extends JpaRepository<Course, Long>{

}
