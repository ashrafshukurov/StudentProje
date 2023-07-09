package com.springboot.project.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Ashraf on 01-Jul-23
 * @project project
 */

@Entity
@Table(name = "users")
@Data
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String userName;
   private String password;
   private String role;


}
