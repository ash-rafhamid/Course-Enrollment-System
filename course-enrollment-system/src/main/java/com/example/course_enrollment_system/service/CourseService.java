package com.example.course_enrollment_system.service;

import com.example.course_enrollment_system.entity.Course;
import com.example.course_enrollment_system.exception.CourseNotFoundException;
import com.example.course_enrollment_system.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void addCourse(Course course){
        courseRepository.save(course);
    }

    public Course getCourseById(Long id){
        return courseRepository.findById(id).orElseThrow(()-> new CourseNotFoundException("Course not found"));
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }
}
