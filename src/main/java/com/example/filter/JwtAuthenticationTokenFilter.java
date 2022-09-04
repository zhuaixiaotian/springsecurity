package com.example.filter;

import com.example.domain.loginuser;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private  RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token))
        {
            filterChain.doFilter(request,response);
            return;
        }
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
             userid= claims.getSubject();
        } catch (Exception e) {
                throw new RuntimeException("token异常");
        }

        String rediskey="login:"+userid;
        loginuser loginuser =(loginuser) redisCache.getCacheObject(rediskey);
        if (loginuser == null)
        {
            throw new RuntimeException("用户未登录");
        }
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(loginuser,null,loginuser.getAuthorities())
        );

        filterChain.doFilter(request,response);


    }
}