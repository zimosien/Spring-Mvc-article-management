package com.example.restfularticlemanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
//@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "required")
    @Size(min = 3,max = 8,message = "must have 3 to 8 characters")
    @Column(name = "user_name")
    private String userName;
    @Pattern(regexp = "[0-9]{10}",message = "must be 10 digits")
    @Column(name = "national_code")
    private String nationalCode;
//    Spring cannot convert String parameters to any date or time object.
//    so One of the ways to handle this problem is to annotate the field with the @DateTimeFormat annotation.
//    we can use either below codes:
//@DateTimeFormat(pattern = "yyyy-mm-dd")
//   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

    private String birthday ;
    @NotBlank
    private String password ;
    //    category and user are parents of article, therefore deleting any of the two will demand deleting
//    the article associated with them. the solution is:
//    1- using Cascade.ALL (which will delete everything)
//    2- using all the Cascades besides Cascade.REMOVE (but you should turn on the orphan removal)
    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
    private List <Article> articleList = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH},fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

}
