package com.example.course_enrollment_system.controller;

import com.example.course_enrollment_system.dto.CourseRequest;
import com.example.course_enrollment_system.dto.CourseResponse;
import com.example.course_enrollment_system.entity.Course;
import com.example.course_enrollment_system.repository.CourseRepository;
import com.example.course_enrollment_system.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@Tag(name = "Course API", description = "API for managing courses")
public class CourseController {
    private CourseService courseService;

    public CourseController (CourseRepository courseRepository) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseResponse> addCourse(@RequestBody CourseRequest courseRequest){
        CourseResponse saved = courseService.addCourse(courseRequest);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public List<CourseResponse> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

}
