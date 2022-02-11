package com.example.restfularticlemanagement.service;

import java.io.Serializable;
import java.util.List;

public interface GeneralService <T, P extends Serializable>{

    public List<T> findAll();
    T findById(P id);
     T save(T entity);
     void delete(T entity);
}
