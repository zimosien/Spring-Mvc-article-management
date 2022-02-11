package com.example.restfularticlemanagement.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
//@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "national_code")
    private String nationalCode;
//    Spring cannot convert String parameters to any date or time object.
//    so One of the ways to handle this problem is to annotate the field with the @DateTimeFormat annotation.
//    we can use either below codes:
//@DateTimeFormat(pattern = "yyyy-mm-dd")
//   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String birthday ;
    private String password ;
    //    category and user are parents of article, therefore deleting any of the two will demand deleting
//    the article associated with them. the solution is:
//    1- using Cascade.ALL (which will delete everything)
//    2- using all the Cascades besides Cascade.REMOVE (but you should turn on the orphan removal)
    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
    private List <Article> articleList = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH},fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

}
