package com.springboot.project.controller;

import com.springboot.project.dto.RegisterDto;
import com.springboot.project.dto.TokenDto;
import com.springboot.project.dto.request.LoginRequest;
import com.springboot.project.model.TokenType;
import com.springboot.project.security.JwtTokenProvider;
import com.springboot.project.security.UserPrincipal;
import com.springboot.project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication =//bu methodla login olur
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUserName(),
                                loginRequest.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);//client login olunnanda biz onu hansisa contextde saxlamaliyiq melumat olaraq
        TokenDto tokenDto=new TokenDto();
        tokenDto.setAccessToken(tokenProvider.generateToken((UserPrincipal) authentication.getPrincipal(), TokenType.ACCESS_TOKEN));
        tokenDto.setRefreshToken(tokenProvider.generateToken((UserPrincipal) authentication.getPrincipal(),TokenType.REFRESH_TOKEN));
        return ResponseEntity.ok(tokenDto);

    }

    @PostMapping("/refresh")//bura sorgu gondermek ucun user gerek login olsun
    public ResponseEntity<TokenDto> refreshToken(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        TokenDto tokenDto=new TokenDto();
        tokenDto.setAccessToken(tokenProvider.generateToken(userPrincipal, TokenType.ACCESS_TOKEN));
        tokenDto.setRefreshToken(tokenProvider.generateToken(userPrincipal,TokenType.REFRESH_TOKEN));
        return ResponseEntity.ok(tokenDto);

    }
    @PostMapping("/register")
    public void register(@RequestBody RegisterDto registerDto){
        authService.register(registerDto);
    }
    @GetMapping("/hash")
    public ResponseEntity<String> getHashedMsg(@RequestParam String msg) throws NoSuchAlgorithmException {
        return ResponseEntity.ok(authService.hash(msg));

    }
    }


