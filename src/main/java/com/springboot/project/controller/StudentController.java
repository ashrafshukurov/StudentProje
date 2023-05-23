package com.springboot.project.controller;

import com.springboot.project.model.Student;
import com.springboot.project.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("v1/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping()
    public ResponseEntity<String> insert(@RequestBody Student student)  {
        studentService.addStudent(student);
        return ResponseEntity.ok("Data added successfully");
    }
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student with id " + id + " is deleted successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudentById(@PathVariable int id, @RequestBody Student student)  {
        studentService.updateStudent(id, student);
        return ResponseEntity.ok("Data updated  successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
}
