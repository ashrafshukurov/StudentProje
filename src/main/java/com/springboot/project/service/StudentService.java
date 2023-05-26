package com.springboot.project.service;

import com.springboot.project.dto.StudentDto;

import java.util.List;

public interface StudentService {
    void addStudent(StudentDto studentDto);
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(int id);
    void updateStudent(int id,StudentDto studentDto);
    void deleteStudent(int id);


}
