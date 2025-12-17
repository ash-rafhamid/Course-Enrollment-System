package com.example.course_enrollment_system.service;

import com.example.course_enrollment_system.entity.Student;
import com.example.course_enrollment_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student){
        studentRepository.save(student);
    }
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
    }


}
