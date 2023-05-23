package com.springboot.project.repository.imp;

import com.springboot.project.model.Student;
import com.springboot.project.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor

public class StudentRepositoryImp implements StudentRepository {



    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;


    @Override
    public void delete(int id)  {
           try(Connection connection=DriverManager.getConnection(db_url,user,password)){
               PreparedStatement preparedStatement=connection.prepareStatement("delete from student where id=?");
               preparedStatement.setInt(1,id);
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
    }

    @Override
    public void save(Student student) {
        try( Connection connection= DriverManager.getConnection(db_url,user,password)){
            PreparedStatement stm=connection.prepareStatement("insert into student(id,name,email) values (?,?,?)");
            stm.setInt(1,student.getId());
            stm.setString(2,student.getName());
            stm.setString(3,student.getEmail());
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void update(int id, Student student) {
     try(Connection connection=DriverManager.getConnection(db_url,user,password)){
         PreparedStatement ps=connection.prepareStatement("Update student SET name=?,email=? where id=?");
         ps.setInt(3,id);
         ps.setString(1,student.getName());
         ps.setString(2,student.getEmail());
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }

    }

    @Override
    public List<Student> findAll() {
        List<Student> list=new ArrayList<>();
       try(Connection connection=DriverManager.getConnection(db_url,user,password)){
           Statement statement=connection.createStatement();
           ResultSet resultSet=statement.executeQuery("select *from student");
           while(resultSet.next()){
               System.out.println(resultSet.getInt("id"));
               System.out.println(resultSet.getString("name"));
               System.out.println(resultSet.getString("email"));
               list.add(new Student(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("email")));
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
        return list;
    }

    @Override
    public Student findById(int id) {
        try(Connection connection=DriverManager.getConnection(db_url,user,password)){
           PreparedStatement ps=connection.prepareStatement("SELECT * from student where id=?");
            ps.setInt(1,id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
