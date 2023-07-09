package com.springboot.project.service;

import com.springboot.project.model.User;
import com.springboot.project.repository.UserRepository;
import com.springboot.project.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("invalid username"));
        UserPrincipal userPrincipal=new UserPrincipal();
        userPrincipal.setId(user.getId());
        userPrincipal.setUsername(user.getUserName());
        userPrincipal.setPassword(user.getPassword());
//        userPrincipal.setRole(user.getRole());
        return userPrincipal;
    }
}
