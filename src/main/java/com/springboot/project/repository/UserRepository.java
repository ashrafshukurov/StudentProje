package com.springboot.project.repository;

import com.springboot.project.model.User;
import com.springboot.project.security.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Ashraf on 01-Jul-23
 * @project project
 */

public interface UserRepository extends JpaRepository<User,Long> {
   Optional<User> findByUserName(String userName);
}
