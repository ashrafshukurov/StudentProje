package com.springboot.project.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherResponse {
    private Long id;
    private String teacherName;
    private String teacherSurname;
    private int teacherAge;

}
