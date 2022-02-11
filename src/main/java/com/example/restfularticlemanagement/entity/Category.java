package com.example.restfularticlemanagement.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
//@ToString
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;

    //    category and user are parents of article, therefore deleting any of the two will demand deleting
//    the article associated with them. the solution is:
//    1- using Cascade.ALL (which will delete everything)
//    2- using all the Cascades besides Cascade.REMOVE (but you should turn on the orphan removal)
    @OneToMany(mappedBy = "category",cascade =CascadeType.ALL,orphanRemoval = true)
    private List<Article> articleList= new ArrayList<>();

}
