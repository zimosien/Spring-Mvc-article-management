package com.example.restfularticlemanagement.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    private String title;
    private String brief;
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
    @JoinColumn(name = "article_user_fk")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private User user;
    @JoinColumn(name = "article_category_fk")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();
}
