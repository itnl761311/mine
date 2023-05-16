package com.mine.service;

import antlr.StringUtils;
import com.mine.entity.Role;
import com.mine.entity.User;
import com.mine.jwt.JwtTokenProvider;
import com.mine.client.dto.UserDtoReq;
import com.mine.client.dto.UserDtoRes;
import com.mine.repository.UserRepository;
import com.mine.security.UserDetailSecurity;
import com.mine.util.Response;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service(value = "main")
public class UserImpl implements IUser {

    AuthenticationManager authenticationManager;
    JwtTokenProvider tokenProvider;

    PasswordEncoder passwordEncoder;

    UserRepository userRepository;
    IRole iRole;

    IRabbitMq iRabbitMq;

    public UserImpl(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder, UserRepository userRepository, IRole iRole, IRabbitMq iRabbitMq) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.iRole = iRole;
        this.iRabbitMq = iRabbitMq;

    }

    @Override
    public ResponseEntity<?> login(UserDtoReq request) {
        Authentication authentication = null;

        UserDtoRes userDtoRes = new UserDtoRes();
        Response response = new Response();

        try {
            // Xác thực từ username và password.
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        } catch (Exception e) {
            userDtoRes.setMessage("Email or password incorrect");
            userDtoRes.setCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken((UserDetailSecurity) authentication.getPrincipal());
        userDtoRes.setMessage("Login successful");
        userDtoRes.setCode(HttpStatus.OK.value());
        userDtoRes.setJwt(jwt);
        return ResponseEntity.status(HttpStatus.OK).body(userDtoRes);
    }

    @Transactional
    @Override
    public ResponseEntity<?> register(UserDtoReq request) {
        UserDtoRes userDtoRes = new UserDtoRes();
        Response response = new Response();

        User user = userRepository.findUserByEmail(request.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setName(request.getName());
            userRepository.save(user);
            setRoleDefault(user);
            userRepository.save(user);
            userDtoRes.setCode(HttpStatus.OK.value());
            userDtoRes.setMessage("Register successful");
            try {
                iRabbitMq.publishUserRegister(user.getId());
            } catch (Exception e) {
                System.out.println("Do not sent email when register" + user);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        userDtoRes.setMessage("Register fail");
        userDtoRes.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @Override
    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    private void setRoleDefault(User user){
        Set<Role> setRolesDefault = new HashSet<>();
        Role role = iRole.findRoleById(1); // idRole = 1 is the default.
        setRolesDefault.add(role);
        if(user != null || user.getId() != 0 || role != null){
            user.setRoles(setRolesDefault);
        }
    }
}
