package com.example.restfularticlemanagement.service;

import com.example.restfularticlemanagement.dao.CategoryDao;
import com.example.restfularticlemanagement.entity.Category;
import com.example.restfularticlemanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements GeneralService<Category, Integer> {
    private CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Integer id) {
        Optional<Category> byId = categoryDao.findById(id);
        if (byId.isPresent())
            return byId.get();
        else throw new RuntimeException("Category not found!");
    }

    @Override
    public Category save(Category entity) {
        return categoryDao.save(entity);
    }

    @Override
    public void delete(Category entity) {
        categoryDao.delete(entity);
    }
}
