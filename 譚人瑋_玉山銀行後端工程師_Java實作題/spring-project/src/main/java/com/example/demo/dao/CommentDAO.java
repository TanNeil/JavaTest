package com.example.demo.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Comment;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
    void deleteByPostId(Long postId);
}
