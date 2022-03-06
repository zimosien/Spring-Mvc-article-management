package com.example.restfularticlemanagement.dao;


import com.example.restfularticlemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao  extends JpaRepository<User,Integer> {
    public User findByUserName (String userName);
    public User findByNationalCode (String nationalCode);
    public Boolean existsUserByUserName (String userName);
    public Boolean existsUserByNationalCode (String nationalCode);
}
