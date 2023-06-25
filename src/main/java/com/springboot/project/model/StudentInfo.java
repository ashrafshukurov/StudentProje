package com.springboot.project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "student_info")
@Data
public class StudentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String phoneNumber;


}
