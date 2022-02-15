package com.example.restfularticlemanagement.service;

import com.example.restfularticlemanagement.dao.RoleDao;
import com.example.restfularticlemanagement.entity.Role;
import com.example.restfularticlemanagement.entity.User;
import com.example.restfularticlemanagement.exception.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements GeneralService<Role, Integer> {
    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional()
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Transactional()
    @Override
    public Role findById(Integer id) {
        Optional<Role> byId = roleDao.findById(id);
        if (byId.isPresent())
            return byId.get();
        else throw new RoleNotFoundException("Role not found!");
    }

    @Transactional()
    @Override
    public Role save(Role entity) {
        return roleDao.save(entity);
    }

    @Transactional()
    @Override
    public void delete(Role entity) {
        roleDao.delete(entity);
    }

    @Transactional()
    public Role findRoleByTitle (String title){
        Role roleByTitle = roleDao.findRoleByTitle(title);
        return roleByTitle;
    }
}
