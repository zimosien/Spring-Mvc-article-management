package com.example.restfularticlemanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "required")
    @Size(max = 100,message = "Maximum of 100 characters")
    private String title;
    @NotBlank(message = "required")
    @Size(max = 1000,message = "Maximum of 1000 characters")
    private String brief;
    @NotBlank(message = "required")
    private String content;
    @Column(name = "create_date")
    //  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String createDate;
    @Column(name = "last_update_date")
    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String lastUpdateDate;
    @Column(name = "publish_date")
    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String publishDate;
    @Column(name = "is_published")
    private Boolean isPublished;
    @JsonBackReference
    @JoinColumn(name = "article_user_fk")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private User user;
    @JsonBackReference
    @JoinColumn(name = "article_category_fk")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();
}
