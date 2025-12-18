package com.example.course_enrollment_system.controller;

import com.example.course_enrollment_system.dto.StudentRequest;
import com.example.course_enrollment_system.dto.StudentResponse;
import com.example.course_enrollment_system.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public StudentResponse addStudent(@RequestBody StudentRequest studentRequest){
        return studentService.addStudent(studentRequest);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public  StudentResponse getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

}
