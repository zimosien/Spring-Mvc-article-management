package com.example.restfularticlemanagement.dao;


import com.example.restfularticlemanagement.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDao  extends JpaRepository<Tag,Integer> {
}
