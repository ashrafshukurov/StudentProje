package com.springboot.project.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "teacher")

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teacherName;
    private String teacherSurname;
    private int teacherAge;

    @ManyToMany(mappedBy = "teachers")
    private Set<Student> students=new HashSet<>();

    public void addStudent(Student student){
        this.students.add(student);
        student.getTeachers().add(this);
    }




}
