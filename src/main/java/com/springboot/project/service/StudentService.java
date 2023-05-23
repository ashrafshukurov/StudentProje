package com.springboot.project.service;

import com.springboot.project.model.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(int id);
    void updateStudent(int id,Student student);
    void deleteStudent(int id);


}
