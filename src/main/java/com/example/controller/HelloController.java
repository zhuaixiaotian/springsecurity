package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
   // @PreAuthorize("@ex.hasAuthority('system:dept:list')") 自定义权限认证
   // @PreAuthorize("hasAnyAuthority('admin','test','system:dept:list')") 有一个满足就通过
    @PreAuthorize("hasAuthority('system:test:list')")
    public String hello(){
        return "hello";
    }
}