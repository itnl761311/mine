package com.mine.management.user.controller;

import com.mine.entity.User;
import com.mine.jwt.JwtTokenProvider;
import com.mine.management.user.dto.UserDtoReq;
import com.mine.management.user.dto.UserDtoRes;
import com.mine.repository.UserRepository;
import com.mine.security.UserDetailSecurity;
import com.mine.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    UserRepository userRepository;
    AuthenticationManager authenticationManager;
    JwtTokenProvider tokenProvider;

    PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDtoReq request){

        Authentication authentication = null;

        UserDtoRes userDtoRes = new UserDtoRes();
        Response response = new Response();

        try{
            // Xác thực từ username và password.
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        }catch (Exception e){
            response.setMessage("Email or password incorrect");
            response.setCode(HttpStatus.BAD_REQUEST.value());
            userDtoRes.setResponse(response);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken((UserDetailSecurity) authentication.getPrincipal());
        response.setMessage("Login successful");
        response.setCode(HttpStatus.OK.value());
        userDtoRes.setResponse(response);
        userDtoRes.setJwt(jwt);
        return ResponseEntity.status(HttpStatus.OK).body(userDtoRes);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDtoReq request){

        UserDtoRes userDtoRes = new UserDtoRes();
        Response response = new Response();

        User user = userRepository.findUserByEmail(request.getEmail());
        if(user == null){
            user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setName(request.getName());
            userRepository.save(user);
            response.setMessage("Register successful");
            response.setCode(HttpStatus.OK.value());
            userDtoRes.setResponse(response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response.setMessage("Register fail");
        response.setCode(HttpStatus.BAD_REQUEST.value());
        userDtoRes.setResponse(response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/test")
    public String test(){
        User user = userRepository.findUserById(1);
        return user.getEmail()+ " --- " + user.getName();
    }
}
