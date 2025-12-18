package com.example.course_enrollment_system.dto;

public class CourseResponse {
    private Long id;
    private String name;
    private String description;

    public CourseResponse(Long id, String title, String description) {
        this.id = id;
        this.name = title;
        this.description = description;
    }

    public Long getId(){return id;}
    public String getName(){return name;}
    public String getDescription(){return description;}
}
