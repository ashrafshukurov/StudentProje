package com.springboot.project.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@NonNull
@Builder
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int age;
    private String grade;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "StudentInfo_id", referencedColumnName = "id")
    private StudentInfo studentInfo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private StudentGroup studentGroup;

    public void setGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
        studentGroup.getStudents().add(this);
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id", referencedColumnName = "id")})
    private Set<Teacher> teachers;

    public Set<Teacher> getTeachers() {
        if (teachers == null) {
            teachers = new HashSet<>();
        }
        return teachers;
    }


}
