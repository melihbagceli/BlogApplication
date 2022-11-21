package com.blogapp.all.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "post_comment")
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title", length = 100)
    private String title;
    @Column(name = "published")
    private boolean published = false;
    @Column(name="created_at", insertable = true, updatable = false)
    private LocalDateTime createdAt;
    @CreationTimestamp
    @Column(name="published_at", insertable = true, updatable = true)
    private LocalDateTime publishedAt;
    @Column(name = "content", columnDefinition = "text")
    private String content;
    //Dependency
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    Post post;
    //-- Self Dependency
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private PostComment parentPostComment;
    @JsonBackReference
    @OneToMany(mappedBy = "parentPostComment")
    private Set<PostComment> linkedPostComments;
    //-- Self Dependency
}
