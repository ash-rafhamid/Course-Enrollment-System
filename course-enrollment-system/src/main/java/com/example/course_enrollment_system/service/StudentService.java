package com.example.course_enrollment_system.service;

import com.example.course_enrollment_system.dto.StudentRequest;
import com.example.course_enrollment_system.dto.StudentResponse;
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


    public StudentResponse addStudent(StudentRequest studentRequest){
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        Student saved = studentRepository.save(student);
        return new StudentResponse(saved.getId(),saved.getName(),saved.getEmail());
    }

    public List<StudentResponse>getAllStudents(){
        return studentRepository.findAll().stream().map
                (s->new StudentResponse(s.getId(),s.getName(),s.getEmail())).toList();

    }




    public StudentResponse getStudentById(Long id){
        Student s = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
        return  new StudentResponse(s.getId(),s.getName(),s.getEmail());
    }


}
