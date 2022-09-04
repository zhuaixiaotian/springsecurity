package com.example.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class loginuser implements UserDetails {
    private User user;

    //存储权限信息
    private List<String> permissions;


    public loginuser(User user,List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }


    //存储SpringSecurity所需要的权限信息的集合
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    @Override
    public  Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities==null)
            authorities = permissions.stream().
                    map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

        //把permissions中字符串类型的权限信息转换成GrantedAuthority对象存入authorities中

        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
