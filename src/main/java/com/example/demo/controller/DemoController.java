package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Deprecated
@RequestMapping("/api")
public class DemoController {

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/hello")
    public String getMethodName(@RequestHeader("Authorization") String authHeader) {
        String userName = jwtUtil.extractUserNames(authHeader.substring(7));
        return "Server Says Hello to " + userName;
    }

}
