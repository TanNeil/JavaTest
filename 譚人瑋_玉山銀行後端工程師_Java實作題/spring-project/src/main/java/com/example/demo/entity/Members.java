package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "members")
public class Members {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "biography")
    private String biography;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
   

}
