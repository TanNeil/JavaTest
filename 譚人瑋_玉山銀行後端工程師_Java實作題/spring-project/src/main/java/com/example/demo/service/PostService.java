package com.example.demo.service;
import com.example.demo.entity.Post;
import com.example.demo.entity.Members;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post savePost(Post post);  
    List<Post> getAllPosts();  
    List<Post> getPostsByUser(Long userId);
    Optional<Post> getPostById(Long id);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);

    
    void addComment(Long postId, String content, Members user);

    
    void deleteCommentsByPostId(Long postId);
}
