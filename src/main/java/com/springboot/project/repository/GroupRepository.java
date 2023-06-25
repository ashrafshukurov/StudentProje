package com.springboot.project.repository;

import com.springboot.project.model.StudentGroup;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<StudentGroup,Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,attributePaths = {
            "students","students.studentInfo","students.teachers" })
    List<StudentGroup> findAll();
}
