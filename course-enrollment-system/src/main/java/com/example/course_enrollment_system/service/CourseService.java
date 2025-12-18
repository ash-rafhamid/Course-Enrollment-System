package com.example.course_enrollment_system.service;

import com.example.course_enrollment_system.dto.CourseRequest;
import com.example.course_enrollment_system.dto.CourseResponse;
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

    public CourseResponse addCourse(CourseRequest courseRequest){
        Course course = new Course();
        course.setTitle(courseRequest.getname());
        course.setDescription(courseRequest.getdescription());
        Course saved = courseRepository.save(course);
        return new CourseResponse(saved.getId(), saved.getTitle(), saved.getDescription());
    }

    public CourseResponse getCourseById(Long id){
        Course course = courseRepository.findById(id).orElseThrow(()-> new CourseNotFoundException("Course not found"));
        return new CourseResponse(course.getId(), course.getTitle(), course.getDescription());
    }

    public List<CourseResponse> getAllCourses(){
        return courseRepository.findAll().stream().map
                (course -> new CourseResponse(course.getId(), course.getTitle(), course.getDescription())).toList();

    }
}
