package com.springboot.project.mapper;


import com.springboot.project.dto.request.StudentRequest;
import com.springboot.project.dto.response.StudentResponse;
import com.springboot.project.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface StudentMapper {

    @Mapping(target = "id",ignore = true)
//    @Mapping(target = "studentInfo",ignore = true)
    Student studentRequestToStudent(StudentRequest studentRequest);

//    @Mapping(target = "fileName",ignore = true)
    StudentResponse studentToStudentResponse(Student student);


}
