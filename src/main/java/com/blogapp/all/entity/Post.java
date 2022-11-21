package com.blogapp.all.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "posts")
public class Post {
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
    @Column(name = "summary", length = 100)
    private String summary;
    @Column(name = "published")
    private boolean published = false;
    @CreationTimestamp
    @Column(name="created_at", insertable = true, updatable = false)
    private LocalDateTime createdAt;
    @CreationTimestamp
    @Column(name="updated_at", insertable = false, updatable = true)
    private LocalDateTime updatedAt;
    @CreationTimestamp
    @Column(name="published_at", insertable = true, updatable = true)
    private LocalDateTime publishedAt;
    @Column(name = "content", columnDefinition = "text")
    private String content;
    //Dependencies
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "post")
    Set<PostComment> postComments;
    //-- Self Dependency
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Post parentPost;
    @JsonBackReference
    @OneToMany(mappedBy = "parentPost")
    private Set<Post> linkedPosts;
    //-- Self Dependency
    @JoinTable(
            name = "post_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @ManyToMany(fetch = FetchType.LAZY)
    Set<Category> categories;
    //Tag Relation
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;
    //Post Meta Relations
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private Set<PostMeta> postMetas;

}
