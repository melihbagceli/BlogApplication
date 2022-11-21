package com.blogapp.all.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", length = 75)
    private String title;
    @Column(name = "meta_title", length = 100)
    private String metaTitle;
    @Column(name = "slug", length = 100)
    private String slug;
    @Column(name = "content", columnDefinition = "text")
    private String content;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Set<Post> posts;
}
