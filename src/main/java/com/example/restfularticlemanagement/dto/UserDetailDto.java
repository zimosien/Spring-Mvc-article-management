package com.example.restfularticlemanagement.dto;

import com.example.restfularticlemanagement.entity.Role;
import com.example.restfularticlemanagement.entity.User;
import lombok.AllArgsConstructor;
import net.bytebuddy.build.Plugin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public class UserDetailDto implements UserDetails {

    private User user;

    public UserDetailDto(User user) {
        this.user = user;
    }
    @Transactional
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role :user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getTitle()));
        }
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
