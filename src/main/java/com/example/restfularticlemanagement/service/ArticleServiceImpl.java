package com.example.restfularticlemanagement.service;

import com.example.restfularticlemanagement.dao.ArticleDao;
import com.example.restfularticlemanagement.entity.Article;
import com.example.restfularticlemanagement.entity.User;
import com.example.restfularticlemanagement.exception.ArticleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements GeneralService<Article, Integer> {
    private ArticleDao articleDao;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Transactional()
    @Override
    public List<Article> findAll() {
        return articleDao.findAll();
    }

    @Transactional()
    @Override
    public Article findById(Integer id) {
        Optional<Article> byId = articleDao.findById(id);
        if (byId.isPresent())
            return byId.get();
        else throw new ArticleNotFoundException("Article not found!");
    }

    @Transactional()
    @Override
    public Article save(Article entity) {
        return articleDao.save(entity);
    }

    @Transactional()
    @Override
    public void delete(Article entity) {
        articleDao.delete(entity);
    }
}
