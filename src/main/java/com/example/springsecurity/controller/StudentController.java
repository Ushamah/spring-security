package com.example.springsecurity.controller;

import java.util.List;

import com.example.springsecurity.data.MockStudentRepository;
import com.example.springsecurity.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    public static final List<Student> students = MockStudentRepository.getAllStudents();
    
    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){

        return students.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + " doesn't exist!"));
    }
}
