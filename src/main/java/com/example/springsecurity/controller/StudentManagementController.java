package com.example.springsecurity.controller;

import java.util.List;

import com.example.springsecurity.mock.MockStudentRepository;
import com.example.springsecurity.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    public static final List<Student> STUDENTS = MockStudentRepository.getAllStudents();

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINEE')")
    public List<Student> getAllStudents() {
        return STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('course:write')")
    public void registerNewStudent(@RequestBody Student student) {
        MockStudentRepository.addStudent(student);

    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAuthority('course:write')")
    public void deleteStudent(@PathVariable("studentId")Integer studentId) {
        MockStudentRepository.deleteStudentById(studentId);
    }

    @PutMapping("/{studentId}")
    @PreAuthorize("hasAuthority('course:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId, Student student) {
       var existingStudent = MockStudentRepository.getStudentById(studentId);
       MockStudentRepository.addStudent(existingStudent);
    }

}
