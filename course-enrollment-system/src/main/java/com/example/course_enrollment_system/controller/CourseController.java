package com.example.course_enrollment_system.controller;

import com.example.course_enrollment_system.entity.Course;
import com.example.course_enrollment_system.repository.CourseRepository;
import com.example.course_enrollment_system.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private CourseService courseService;

    public CourseController (CourseRepository courseRepository) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        Course saved = courseService.addCourse(course);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping("/{id}")
    public Course getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

}
