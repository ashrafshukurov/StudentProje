package com.springboot.project.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ashraf on 02-Jul-23
 * @project project
 */
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

   @Override
   public void commence(HttpServletRequest request,
                        HttpServletResponse response,
                        AuthenticationException e) throws IOException, ServletException {
      log.error("Responding With unauthorized error.Message -{}",e.getMessage());
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
   }
}
