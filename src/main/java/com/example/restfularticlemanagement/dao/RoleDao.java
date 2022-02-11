package com.example.restfularticlemanagement.dao;


import com.example.restfularticlemanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao  extends JpaRepository<Role,Integer> {
    Role findRoleByTitle (String title);
}
