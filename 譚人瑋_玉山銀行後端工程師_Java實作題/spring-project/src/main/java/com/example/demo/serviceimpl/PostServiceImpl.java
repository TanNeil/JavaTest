package com.example.demo.serviceimpl;

import com.example.demo.dao.PostDAO;
import com.example.demo.dao.CommentDAO;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Members;
import com.example.demo.entity.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public Post savePost(Post post) {
        return postDAO.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postDAO.findAll();
    }

    @Override
    public List<Post> getPostsByUser(Long userId) {
        return postDAO.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postDAO.findById(id);
    }

    @Override
    public Post updatePost(Long id, Post post) {
        post.setId(id);
        return postDAO.save(post);
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        
        commentDAO.deleteByPostId(id); 
        postDAO.deleteById(id);
    }

    @Override
    public void addComment(Long postId, String content, Members user) {
        Post post = postDAO.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setPost(post);

        commentDAO.save(comment);
    }

    @Override
    @Transactional
    public void deleteCommentsByPostId(Long postId) {
        commentDAO.deleteByPostId(postId);
    }
}
