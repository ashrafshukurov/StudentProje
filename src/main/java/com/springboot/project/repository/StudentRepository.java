package com.springboot.project.repository;

import com.springboot.project.model.Student;

import java.util.List;

public interface StudentRepository {
    void delete(int id);
    void save(Student student);
    void update(int id, Student student);
    List<Student> findAll();
    Student findById(int id);

}
