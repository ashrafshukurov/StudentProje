package com.springboot.project.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@NonNull
@Builder
public class Student {
    private Long id;
    private String name;
    private String email;
}
