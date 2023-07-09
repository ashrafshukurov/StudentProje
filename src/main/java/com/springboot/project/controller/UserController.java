package com.springboot.project.controller;

import com.springboot.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * @author Ashraf on 02-Jul-23
 * @project project
 */

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/{id}")
    public String findUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
