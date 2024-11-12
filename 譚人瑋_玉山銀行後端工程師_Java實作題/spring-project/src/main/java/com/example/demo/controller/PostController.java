package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.Members;
import com.example.demo.service.PostService;
import com.example.demo.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private MembersService membersService;

    
    @GetMapping("/list")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "list";
    }

    
    @GetMapping("/new")
    public String newPost() {
        return "new";
    }

    
    @PostMapping("/post_new")
    public String createPost(@RequestParam("content") String content) {
        Members user = membersService.getCurrentUser();
        Post post = new Post();
        post.setUser(user);
        post.setContent(content);

        postService.savePost(post);
        return "redirect:/list";
    }

    
    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable("id") Long id, Model model) {
        Post post = postService.getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        model.addAttribute("post", post);
        return "edit";
    }

    
    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable("id") Long id, @RequestParam("content") String content) {
        Post post = postService.getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setContent(content);
        postService.updatePost(id, post);
        return "redirect:/list"; 
    }

    
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return "redirect:/list";
    }

    @PostMapping("/posts/{postId}/add-comment")
    public String addComment(@PathVariable Long postId, @RequestParam String content) {
        Members user = membersService.getCurrentUser();
        postService.addComment(postId, content, user);
        return "redirect:/list";
    }
}
