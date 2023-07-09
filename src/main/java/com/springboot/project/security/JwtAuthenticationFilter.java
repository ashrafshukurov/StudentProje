package com.springboot.project.security;

import com.springboot.project.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    //her defe sorgu gelende sorgu gelib bura catacaq
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws
            ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            if (StringUtils.hasText(jwt) &&
                    tokenProvider.validateToken(jwt)) {
                String userName =
                        tokenProvider.getUserNameFromJWT(jwt);
                UserDetails userDetails =
                        customUserDetailsService.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken
                        authentication = new
                        UsernamePasswordAuthenticationToken(userDetails, null,
                      userDetails.getAuthorities());//hashset onun hara privelegiyasi varsa onlardir principal userin infosu olacaq credentials hemise null olacaq
                authentication.setDetails(new
                        WebAuthenticationDetailsSource().buildDetails(request));
                //login olmaq ucun SecurityContextHolder a elave etmeliyik
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }
        filterChain.doFilter(request, response);
    }
    private String getJwtFromRequest(HttpServletRequest request)
            //requestden headeri gotrur
    {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) &&
                bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7,
                    bearerToken.length());
        }
        return null;
    }
}
