package com.springboot.project.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class GroupResponse {
    private Long id;
    private String groupName;
    private String groupNumber;
    private List<StudentResponse> studentResponses=new ArrayList<>();
}
