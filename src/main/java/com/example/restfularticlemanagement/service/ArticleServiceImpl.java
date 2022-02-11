package com.example.restfularticlemanagement.service;

import com.example.restfularticlemanagement.dao.ArticleDao;
import com.example.restfularticlemanagement.entity.Article;
import com.example.restfularticlemanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements GeneralService<Article, Integer> {
    private ArticleDao articleDao;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public List<Article> findAll() {
        return articleDao.findAll();
    }

    @Override
    public Article findById(Integer id) {
        Optional<Article> byId = articleDao.findById(id);
        if (byId.isPresent())
            return byId.get();
        else throw new RuntimeException("Article not found!");
    }

    @Override
    public Article save(Article entity) {
        return articleDao.save(entity);
    }

    @Override
    public void delete(Article entity) {
        articleDao.delete(entity);
    }
}
