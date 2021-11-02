package com.example.springsecurity.mock;

import java.util.List;

import com.example.springsecurity.model.Student;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockStudentRepository {


    private static List<Student> students = Lists.newArrayList(
            new Student(1, "Ushamah"),
            new Student(2, "Refia")
    );


    public static List<Student> getAllStudents() {
        return students;
    }

    public static void addStudent(Student student) {
        final Student existingStudent = getStudentById(student.getStudentId());
        if (existingStudent.getStudentId() == null) {
            students.add(student);
            log.info("Student {} registered successfully", student.getStudentName());
        }
        existingStudent.setStudentId(student.getStudentId());
        existingStudent.setStudentName(student.getStudentName());
        log.info("Student: {} updated successfully", existingStudent.getStudentName());
    }

    public static void deleteStudentById(Integer studentId) {
        Student student = students.stream()
                .filter(s -> studentId.equals(s.getStudentId()))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Student with id: " + studentId + " doesn't exist!"));
        students.remove(student);
        log.info("Student: {} delete successfully", student.getStudentName());
    }

    public static Student getStudentById(Integer studentId) {
        return students.stream()
                .filter(s -> studentId.equals(s.getStudentId()))
                .findFirst()
                .orElse(new Student(null, null));
    }

    public static void updateStudent(Integer studentId, Student student) {
        var existingStudent = MockStudentRepository.getStudentById(studentId);
        if (existingStudent.getStudentId() == null) {
            addStudent(student);
        }
        existingStudent.setStudentName(student.getStudentName());
    }
}