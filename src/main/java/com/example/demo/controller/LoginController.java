package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.LoginRequest;
import com.example.demo.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/ping")
    public String ping() {
        return "Controller working!";
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest) {

        return loginService.loginUser(loginRequest);
    }

}
