package com.example.restfularticlemanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "required")
    private String title;
    private String description;

    //    category and user are parents of article, therefore deleting any of the two will demand deleting
//    the article associated with them. the solution is:
//    1- using Cascade.ALL (which will delete everything)
//    2- using all the Cascades besides Cascade.REMOVE (but you should turn on the orphan removal)
    @JsonManagedReference
    @OneToMany(mappedBy = "category",cascade =CascadeType.ALL,orphanRemoval = true)
    private List<Article> articleList= new ArrayList<>();

}
