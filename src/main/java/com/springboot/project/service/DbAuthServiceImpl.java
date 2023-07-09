package com.springboot.project.service;

import com.springboot.project.dto.RegisterDto;
import com.springboot.project.model.User;
import com.springboot.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author Ashraf on 01-Jul-23
 * @project project
 */

@Service
@RequiredArgsConstructor
public class DbAuthServiceImpl implements AuthService {
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;

   @Override
   public boolean login(String username, String password) {
      return true;
   }

   @Override
   public void register(RegisterDto registerDto) {
      User user=new User();
      user.setUserName(registerDto.getUserName());
      user.setName(registerDto.getName());
      user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
      userRepository.save(user);
   }

   @Override
   public String hash(String msg) throws NoSuchAlgorithmException {

      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(msg.getBytes(StandardCharsets.UTF_8));
// Convert byte array into signum representation
      BigInteger no = new BigInteger(1, messageDigest);
// Convert message digest into hex value
      String hashtext = no.toString(16);
      while (hashtext.length() < 32) {
         hashtext = "0" + hashtext;
      }
      return hashtext;

   }
}
