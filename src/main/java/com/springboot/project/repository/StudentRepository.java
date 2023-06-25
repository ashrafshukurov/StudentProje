package com.springboot.project.repository;

import com.springboot.project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{


}
