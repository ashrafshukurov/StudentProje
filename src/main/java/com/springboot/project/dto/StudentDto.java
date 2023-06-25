package com.springboot.project.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel("Details about Student")
public class StudentDto {
    @ApiModelProperty(notes = "Student Id",example ="1",required =true)
    private Long id;
    @ApiModelProperty(notes = "Student Name",example = "Ashraf",required = true)
    private String name;
    @ApiModelProperty(notes = "Student Email",example = "AshrafShukurov@gmail.com",required = true)
    private String email;
    private int age;

}
