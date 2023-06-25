package com.springboot.project.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequest {
    private String name;
    private String email;
    private int age;
    private String grade;
    private String address;
    private String phoneNumber;
    private String groupName;
    private String groupNumber;
    private String teacherName;
    private String teacherSurname;
    private int teacherAge;

}
