package com.example.restfularticlemanagement;

import com.example.restfularticlemanagement.dao.ArticleDao;
import com.example.restfularticlemanagement.entity.Article;
import com.example.restfularticlemanagement.entity.Category;
import com.example.restfularticlemanagement.entity.Tag;
import com.example.restfularticlemanagement.entity.User;
import com.example.restfularticlemanagement.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication
public class ResTfulArticleManagementApplication {


    public static void main(String[] args) {
        SpringApplication.run(ResTfulArticleManagementApplication.class, args);

    }

}
