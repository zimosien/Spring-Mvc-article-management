package com.example.restfularticlemanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MyBeans {

    @Bean
    public PasswordEncoder customEncoder2() {
        return new Argon2PasswordEncoder();
    }
    @Primary
    @Bean
    public PasswordEncoder customEncoder() {
        return new BCryptPasswordEncoder();
    }
}
