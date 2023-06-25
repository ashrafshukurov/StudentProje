package com.springboot.project.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String name;
    private String email;
    private int age;
    private String grade;
    private String fileName;
    private List<TeacherResponse> teacherResponses=new ArrayList<>();

}
