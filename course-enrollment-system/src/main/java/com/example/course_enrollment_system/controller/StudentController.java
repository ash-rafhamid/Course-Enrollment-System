package com.example.course_enrollment_system.controller;

import com.example.course_enrollment_system.entity.Student;
import com.example.course_enrollment_system.repository.StudentRepository;
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
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public  Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

}
