package com.rahul.userservicev1.controllers;

import com.rahul.userservicev1.dtos.*;
import com.rahul.userservicev1.services.AuthService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@RestController()
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService ) {
        this.authService = authService;


    }

    @PostMapping("/sign_up")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto request){
        SignUpResponseDto response = new SignUpResponseDto();

        try{
            if(authService.signUp(request.getEmail() , request.getPassword())){
                response.setRequestStatus(RequestStatus.SUCCESS);
            }
            else {
                response.setRequestStatus(RequestStatus.FAILURE);
            }
            return new ResponseEntity<>(response , HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            response.setRequestStatus(RequestStatus.FAILURE);
            return new ResponseEntity<>(response , HttpStatus.CONFLICT);
        }

    }



    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto){

        try{
            String token = authService.login(requestDto.getEmail(), requestDto.getPassword());

            LoginResponseDto loginDto = new LoginResponseDto();
            loginDto.setRequestStatus(RequestStatus.SUCCESS);

            MultiValueMap<String , String> headers = new LinkedMultiValueMap<>();
            headers.add("AUTH_TOKEN", token);
            ResponseEntity<LoginResponseDto> response = new ResponseEntity<>(
                    loginDto,headers , HttpStatus.OK
            );
            return response;
        }
        catch(Exception e){
            LoginResponseDto loginDto = new LoginResponseDto();
            loginDto.setRequestStatus(RequestStatus.FAILURE);
            ResponseEntity<LoginResponseDto> response = new ResponseEntity<>(
                    loginDto, null , HttpStatus.BAD_REQUEST
            );
            return response;
        }

    }


    @GetMapping("/validate")
    public boolean validate(String token){
        return authService.validate(token);

    }
}
