package com.blogapp.all.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "post_meta")
public class PostMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "key", length = 50)
    private String key;
    @Column(name = "content", columnDefinition = "text")
    private String content;
    //Dependency
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
