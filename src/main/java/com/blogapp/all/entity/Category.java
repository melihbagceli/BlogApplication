package com.blogapp.all.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title", length = 75)
    private String title;
    @Column(name = "meta_title", length = 100)
    private String metaTitle;
    @Column(name = "slug", length = 100)
    private String slug;
    @Column(name = "content", columnDefinition = "text")
    private String content;
    //Dependencies
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    @ManyToMany(fetch = FetchType.LAZY)
    Set<Post> posts;

    //-- Self Dependency
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Category parentCategory;
    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCategory")
    private Set<Category> linkedPosts;
    //-- Self Dependency
}
