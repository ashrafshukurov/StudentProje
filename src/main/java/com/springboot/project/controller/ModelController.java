package com.springboot.project.controller;

import com.springboot.project.dto.RegisterDto;
import com.springboot.project.dto.request.StudentRequest;
import com.springboot.project.dto.response.StudentResponse;
import com.springboot.project.security.JwtTokenProvider;
import com.springboot.project.service.AuthService;
import com.springboot.project.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class ModelController {
    private final StudentService studentService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }
    @GetMapping("/signUp")
    public String showSignUpForm(){
        return "signUp";
    }
    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("allStudentList",studentService.getAllStudents());
        return "index";
    }
    @GetMapping("/add")
    public String addStudent(Model model) {
        StudentRequest studentRequest = new StudentRequest();//why we write here student request why we need that
        model.addAttribute("studentRequest", studentRequest);
        return "students";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute StudentRequest studentRequest){
        studentService.addStudent(studentRequest);
        return "students";
    }
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        return"redirect:/";
    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id,Model model){
        StudentResponse studentResponse=studentService.getStudentById(id);
        model.addAttribute("studentResponse",studentResponse);
        return "update";
    }
    @PostMapping("/register")
    public String register(Model model, @ModelAttribute RegisterDto registerDto) {
        authService.register(registerDto);
        model.addAttribute("registerDto", registerDto); // Add a message to be displayed in the view
        return "signUp"; // Return the name of the view to be rendered
    }

}
