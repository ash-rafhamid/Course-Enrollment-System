package com.example.course_enrollment_system.dto;

import com.example.course_enrollment_system.entity.Course;
import com.example.course_enrollment_system.entity.Student;

import java.time.LocalDate;

public class EnrollmentResponse {
    private Long id;
    private Long courseId;
    private Long studentId;
    private LocalDate date;

    public EnrollmentResponse(Long id, Long id1, Long id2, LocalDate date) {
        this.id = id;
        this.courseId = id1;
        this.studentId = id2;
        this.date = date;
    }
    public Long getId(){return id;}
    public Long getCourseId(){return courseId;}
    public Long getStudentId(){return studentId;}
    public LocalDate getEnrolledDate(){return date;}
}
