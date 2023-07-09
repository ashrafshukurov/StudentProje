package com.springboot.project.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ashraf on 01-Jul-23
 * @project project
 */

@Setter
@Getter
public class TokenDto {
   private String accessToken;
   private String refreshToken;
}
