package com.springboot.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "StudentsGroup")
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private String groupNumber;
    @OneToMany(mappedBy = "studentGroup")
    private Set<Student> students = new HashSet<>();


}
