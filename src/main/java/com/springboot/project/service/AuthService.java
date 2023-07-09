package com.springboot.project.service;

import com.springboot.project.dto.RegisterDto;

import java.security.NoSuchAlgorithmException;

/**
 * @author Ashraf on 01-Jul-23
 * @project project
 */

public interface AuthService {
   boolean login(String username, String password);
   void register(RegisterDto registerDto);
   String hash(String msj) throws NoSuchAlgorithmException;

}
