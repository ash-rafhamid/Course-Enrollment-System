package com.example.course_enrollment_system.dto;

public class EnrollmentRequest {

    private Long courseId;
    private Long studentId;

    public Long getcourseId(){return courseId;}
    public Long getstudentId(){return studentId;}
    public void setcourseId(Long courseId){this.courseId = courseId;}
    public void setstudentId(Long studentId){this.studentId = studentId;}
}
