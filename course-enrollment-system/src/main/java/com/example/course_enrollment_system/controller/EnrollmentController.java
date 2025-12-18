package com.example.course_enrollment_system.controller;


import com.example.course_enrollment_system.dto.CourseResponse;
import com.example.course_enrollment_system.dto.EnrollmentResponse;
import com.example.course_enrollment_system.dto.StudentResponse;
import com.example.course_enrollment_system.entity.Course;
import com.example.course_enrollment_system.entity.Enrollment;
import com.example.course_enrollment_system.entity.Student;
import com.example.course_enrollment_system.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public EnrollmentResponse enrollment(Long studentId, Long courseId){
        return enrollmentService.enrollment(studentId,courseId);
    }

    @GetMapping("/student/{studentId}")
    public List<CourseResponse> getCourseByStudent(@PathVariable Long studentId){
        return enrollmentService.getCourseByStudent(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<StudentResponse> getStudnetOfCourse(@PathVariable Long courseId) {
        return enrollmentService.getStudentsOfCourse(courseId);
    }

}
