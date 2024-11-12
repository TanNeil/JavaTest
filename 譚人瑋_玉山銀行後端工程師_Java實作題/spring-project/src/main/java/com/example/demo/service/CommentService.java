package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CommentDAO;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Members;
import com.example.demo.entity.Post;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    private CommentDAO commentRepository;

    
    public Comment addComment(Long postId, Long userId, String content) {
        
        Post post = new Post();  
        post.setId(postId);

        Members user = new Members();  
        user.setId(userId);

        
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());

       
        return commentRepository.save(comment);
    }
}
