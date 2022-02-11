package com.example.restfularticlemanagement.service;

import com.example.restfularticlemanagement.dao.UserDao;
import com.example.restfularticlemanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements GeneralService<User, Integer> {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Integer id) {
        Optional<User> byId = userDao.findById(id);
        if(byId.isPresent())
        return byId.get();
        else throw new RuntimeException("User not found!");
    }

    @Override
    public User save(User entity) {
        return userDao.save(entity);
    }

    @Override
    public void delete(User entity) {
    userDao.delete(entity);
    }

    public User findByUsername(String username){
        User user = userDao.findByUserName(username);
        return user;
    }
}
