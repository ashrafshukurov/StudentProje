package com.springboot.project.service.imp;

import com.springboot.project.dto.StudentDto;
import com.springboot.project.model.Student;
import com.springboot.project.repository.StudentRepository;
import com.springboot.project.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImp implements StudentService {

    private final  StudentRepository studentRepository;
    @Override
    public void addStudent(StudentDto studentDto) {
        try{
            Student student=convertToStudent(studentDto);
            log.info("Student added successfully");
            studentRepository.save(student);
        }catch (Exception e){
            log.error("Student Already exists");
        }

    }

    @Override
    public List<StudentDto> getAllStudents() {
        try {
            List<Student> students=studentRepository.findAll();
            log.info("Students got successfully");
            return students.stream().map(this::convertStudentDto).collect(Collectors.toList());
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public StudentDto getStudentById(int id) {
        try{
            Student student=studentRepository.findById(id);
            log.info("Student got successfully by id");
            return convertStudentDto(student);
        }catch (Exception e){
            log.error("NoSuchStudent");
            return null;
        }

    }

    @Override
    public void updateStudent(int id, StudentDto studentDto) {
        try {
            Student student=convertToStudent(studentDto);
            log.info("Student Successfully updated");
            studentRepository.update(id,student);
        }catch (Exception e){
            log.error("NoSuchStudent");
        }

    }
    @Override
    public void deleteStudent(int id) {
        log.info("Students deleted successfully");
    studentRepository.delete(id);
    }

    private StudentDto convertStudentDto(Student student){
        return StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail()).build();
    }
    private Student convertToStudent(StudentDto studentDto) {
        return new Student(studentDto.getId(), studentDto.getName(), studentDto.getEmail());
    }
}
