package com.springboot.project.service;

import com.springboot.project.dto.StudentDto;
import com.springboot.project.dto.request.StudentRequest;
import com.springboot.project.dto.response.GroupResponse;
import com.springboot.project.dto.response.StudentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    StudentResponse addStudent(StudentRequest studentRequest);
    List<GroupResponse> getAllStudents();
    StudentResponse getStudentById(Long id);
    void updateStudent(Long id,StudentRequest studentRequest);
    void deleteStudent(Long id);


}
