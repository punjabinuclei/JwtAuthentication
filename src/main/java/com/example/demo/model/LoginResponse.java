package com.example.demo.model;

public class LoginResponse {
    private String jwt;

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }

}