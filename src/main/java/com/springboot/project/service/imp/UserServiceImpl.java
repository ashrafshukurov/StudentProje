package com.springboot.project.service.imp;

import com.springboot.project.exception.NoSuchElementException;
import com.springboot.project.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Ashraf on 03-Jul-23
 * @project project
 */

@Service
public class UserServiceImpl implements UserService {
   @Override
   public String getUserById(Long id) {
      if (id == 1) {
         return "Testov Test";
      } else {
         throw new NoSuchElementException("User not found");
      }
   }
}
