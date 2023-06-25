package com.springboot.project.validation;

import com.springboot.project.exception.AgeIsUnLimited;
import org.springframework.stereotype.Component;

@Component
public class AgeChecker {
    public void check(int age){
        if(age<6 || age>18){
            throw new AgeIsUnLimited("Age is ignore");
        }
    }
}
