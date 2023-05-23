package com.springboot.project.service.imp;

import com.springboot.project.model.Student;
import com.springboot.project.repository.StudentRepository;
import com.springboot.project.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService {

    private final  StudentRepository studentRepository;
    public StudentServiceImp(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    @Override
    public void addStudent(Student student) {
          studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
       return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
      return   studentRepository.findById(id);

    }

    @Override
    public void updateStudent(int id, Student student) {

        studentRepository.update(id,student);
    }

    @Override
    public void deleteStudent(int id) {
    studentRepository.delete(id);
    }
}
