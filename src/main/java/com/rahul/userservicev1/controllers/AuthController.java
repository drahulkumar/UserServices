package com.rahul.userservicev1.controllers;

import com.rahul.userservicev1.dtos.LoginRequestDto;
import com.rahul.userservicev1.dtos.LoginResponseDto;
import com.rahul.userservicev1.dtos.SignUpRequestDto;
import com.rahul.userservicev1.dtos.SignUpResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@RestController("/auth")
public class AuthController {

    @PostMapping("/sign_up")
    public SignUpResponseDto signUp(SignUpRequestDto requestDto){
        return null;
    }



    @PostMapping("/login")
    public LoginResponseDto login(LoginRequestDto requestDto){
        return null;
    }
}
