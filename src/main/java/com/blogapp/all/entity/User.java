package com.blogapp.all.entity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "first_name", length = 50)
    private String firstName;
    @Column(name = "middle_name", length = 50)
    private String middleName;
    @Column(name = "last_name", length = 50)
    private String lastName;
    @Column(name = "mobile", length = 15)
    private String mobile;
    @Column(name = "password_hash", length = 32)
    private String passwordHash;
    @CreationTimestamp
    @Column(name="registered_at", insertable = true, updatable = false)
    private LocalDateTime registeredAt;
    @CreationTimestamp
    @Column(name = "last_login", updatable = true)
    private LocalDateTime lastLogin;
    @Column(name = "intro", columnDefinition = "text")
    private String intro;
    @Column(name = "profile", columnDefinition = "text")
    private String profile;
    //Dependencies
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "author")
    Set<Post> posts;
}
