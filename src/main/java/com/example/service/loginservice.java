package com.example.service;

import com.example.domain.User;
import com.example.utils.ResponseResult;
import org.springframework.stereotype.Service;


public interface loginservice  {
    ResponseResult login(User user);

    ResponseResult logout();
}
