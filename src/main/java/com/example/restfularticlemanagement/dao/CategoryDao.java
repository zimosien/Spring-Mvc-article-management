package com.example.restfularticlemanagement.dao;

import com.example.restfularticlemanagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path = "categories")
public interface CategoryDao extends JpaRepository <Category,Integer> {
}
