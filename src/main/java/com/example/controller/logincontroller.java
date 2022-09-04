package com.example.controller;

import com.example.domain.User;
import com.example.service.loginservice;
import com.example.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.image.RescaleOp;
import java.sql.PseudoColumnUsage;

@RestController
public class logincontroller {
    @Autowired
    private loginservice  loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return  loginService.login(user);

    }
    @GetMapping("/user/logout")
    public ResponseResult logout()
    {
        System.out.printf("1111111");
        return  loginService.logout();

    }
}
