package com.example.restfularticlemanagement.service;

import com.example.restfularticlemanagement.dao.CategoryDao;
import com.example.restfularticlemanagement.entity.Category;
import com.example.restfularticlemanagement.entity.User;
import com.example.restfularticlemanagement.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements GeneralService<Category, Integer> {
    private CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Transactional()
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Transactional()
    @Override
    public Category findById(Integer id) {
        Optional<Category> byId = categoryDao.findById(id);
        if (byId.isPresent())
            return byId.get();
        else throw new CategoryNotFoundException("Category not found!");
    }

    @Transactional()
    @Override
    public Category save(Category entity) {
        return categoryDao.save(entity);
    }

    @Transactional()
    @Override
    public void delete(Category entity) {
        categoryDao.delete(entity);
    }
}
