package com.example.restfularticlemanagement.dao;

import com.example.restfularticlemanagement.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository <Article , Integer>{
}
