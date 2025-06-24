package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.LoginRequest;
import com.example.demo.model.LoginResponse;
import com.example.demo.util.JwtUtil;

@SuppressWarnings("deprecation")
@Service
public class LoginService {

    @Autowired
    UserDetailsService userDetailsService;

    
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationProvider authenticationProvider;

    public ResponseEntity<Object> loginUser(LoginRequest loginRequest) {
        try {
            // validate User
            UserDetails userDb = userDetailsService.loadUserByUsername(loginRequest.getEmailId());
            // validate password
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmailId(), loginRequest.getPassword());
            authenticationProvider.authenticate(authenticationToken);
            String token = jwtUtil.generateToken(userDb);
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (UsernameNotFoundException excep) {
            return ResponseEntity.status(404).body("User does not exist");
        }

    }
}
