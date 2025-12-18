package com.example.course_enrollment_system.service;

import com.example.course_enrollment_system.dto.CourseResponse;
import com.example.course_enrollment_system.dto.EnrollmentResponse;
import com.example.course_enrollment_system.dto.StudentResponse;
import com.example.course_enrollment_system.entity.Course;
import com.example.course_enrollment_system.entity.Enrollment;
import com.example.course_enrollment_system.entity.Student;
import com.example.course_enrollment_system.exception.CourseNotFoundException;
import com.example.course_enrollment_system.exception.StudentNotFoundException;
import com.example.course_enrollment_system.repository.CourseRepository;
import com.example.course_enrollment_system.repository.EnrollmentRepository;
import com.example.course_enrollment_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    
    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    
    public EnrollmentResponse enrollment(Long studentId, Long courseId){
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new StudentNotFoundException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new CourseNotFoundException("Course not found"));
        Optional<Enrollment>exisiting = enrollmentRepository.findByStudentAndCourse(student,course);
        if(exisiting.isPresent()){
            throw new RuntimeException("Student already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        Enrollment saved = enrollmentRepository.save(enrollment);
        return new EnrollmentResponse(saved.getId(),
                                    saved.getStudent().getId(),
                                    saved.getCourse().getId(),
                                    saved.getDate());

    }

    public List<CourseResponse> getCourseByStudent(Long studentId){
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new StudentNotFoundException("Student not found"));
        List<Enrollment>enrollments = enrollmentRepository.findByStudent(student);
//        return enrollments.stream().map(Enrollment::getCourse).toList();
        return enrollments.stream().map(e->
                new CourseResponse(e.getCourse().getId(),e.getCourse().getTitle(),e.getCourse().getDescription())).toList();
    }

    public List<StudentResponse>getStudentsOfCourse(Long courseId){
        Course course = courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException("Course not found"));
        List<Enrollment>students = enrollmentRepository.findByCourse(course);
        return students.stream().map(e->
                new StudentResponse(e.getStudent().getId(),e.getStudent().getName(),e.getStudent().getEmail())).toList();

    }

}
