package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.domain.User;
import com.example.domain.loginuser;
import com.example.mapper.menumapper;
import com.example.mapper.usermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

@Service
public class userdetailimpl  implements UserDetailsService {
    @Autowired
    private usermapper userMapper;
    @Autowired
    private menumapper menumapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User ::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
        if (user == null)
        {
            throw  new UsernameNotFoundException("用户名不存在");

        }

        List<String> list= menumapper.selectperm(user.getId());

        return  new loginuser(user,list);
    }
}
