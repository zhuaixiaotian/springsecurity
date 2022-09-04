package com.example;

import com.example.domain.User;
import com.example.mapper.menumapper;
import com.example.mapper.usermapper;
import static com.example.utils.JwtUtil.parseJWT;

import com.example.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static com.example.utils.JwtUtil.createJWT;


@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private usermapper usermapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private com.example.mapper.menumapper menumapper;
    @Test
    void contextLoads() {

        User user = new User();
        user.setNickName("wdda");
        user.setUserName("wdjoa");
        user.setPassword("112");
        usermapper.insert(user);
    }
    @Test
    public  void t()
    {
        System.out.println(passwordEncoder.matches("1234","$2a$10$eBOhtB1ckgrSkdrw0aD1xu7pZuGqpQvpmZye4p1dT/dmr2erx/972"));

    }
    @Test
    public  void f()
    {
        String jwt = createJWT("123");
        try {
            Claims claims = parseJWT(jwt);
            System.out.println(claims.getSubject());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @Test
    public  void  q()
    {
        redisCache.setCacheObject("k1","k2");


    }
    @Test
    public  void q1() {
        System.out.println(menumapper.selectperm(1l));

    }


}
