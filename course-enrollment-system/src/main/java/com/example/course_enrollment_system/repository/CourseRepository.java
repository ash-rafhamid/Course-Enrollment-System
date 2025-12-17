package com.example.course_enrollment_system.repository;

import com.example.course_enrollment_system.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
