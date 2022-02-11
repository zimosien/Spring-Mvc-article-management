package com.example.restfularticlemanagement.service;

import com.example.restfularticlemanagement.dao.UserDao;
import com.example.restfularticlemanagement.dto.UserDetailDto;
import com.example.restfularticlemanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user==null)
            throw new UsernameNotFoundException("User not found");
        return new UserDetailDto(user);
    }
}
