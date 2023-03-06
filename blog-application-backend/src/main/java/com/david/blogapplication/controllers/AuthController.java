package com.david.blogapplication.controllers;

import com.david.blogapplication.exceptions.ApiException;
import com.david.blogapplication.payloads.JwtAuthRequest;
import com.david.blogapplication.payloads.JwtAuthResponse;
import com.david.blogapplication.payloads.UserDto;
import com.david.blogapplication.security.JwtTokenHelper;
import com.david.blogapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = this.userDetailService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try{
            this.authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            System.out.println("Invalid Details");
            throw new ApiException("Invalid Username or Password !!");
        }
    }

    //register new user api
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
        UserDto registerNewUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<>(registerNewUser, HttpStatus.CREATED);
    }
}
