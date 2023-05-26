package com.springboot.project.repository.imp;

import com.springboot.project.model.Student;
import com.springboot.project.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
@RequiredArgsConstructor

public class StudentRepositoryImp implements StudentRepository{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public void delete(int id)  {
        jdbcTemplate.update("DELETE  from student where id=?",id);
    }

    @Override
    public void save(Student student) {
          SimpleJdbcInsert simpleJdbcInsert=new SimpleJdbcInsert(jdbcTemplate).withTableName("student");
          Map<String, Object> map=new HashMap<>();
          map.put("id",student.getId());
          map.put("name",student.getName());
          map.put("email",student.getEmail());
          simpleJdbcInsert.execute(map);
    }

    @Override
    public void update(int id, Student student) {
        jdbcTemplate.update("UPDATE student SET name=?,email=? where id=?",student.getName(),student.getEmail(),id);
    }
    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("Select * from student",BeanPropertyRowMapper.newInstance(Student.class));
    }
    @Override
    public Student findById(int id) {
      return jdbcTemplate.queryForObject("select * from student where id=?", BeanPropertyRowMapper.newInstance(Student.class),id);
    }
}
