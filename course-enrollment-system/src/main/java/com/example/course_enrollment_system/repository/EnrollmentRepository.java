package com.example.course_enrollment_system.repository;

import com.example.course_enrollment_system.entity.Course;
import com.example.course_enrollment_system.entity.Enrollment;
import com.example.course_enrollment_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);

    List<Enrollment> findByStudent(Student student);
}
