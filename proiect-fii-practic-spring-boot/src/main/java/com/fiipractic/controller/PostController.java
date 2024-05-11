package com.fiipractic.controller;

import com.fiipractic.model.Post;
import com.fiipractic.service.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/posts/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPost(@RequestBody Post post, @PathVariable String userId) {
        postService.addPost(post, userId);
    }

    @GetMapping(value = "/users/{userId}/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getOwnPosts(@PathVariable String userId) {
        return postService.getOwnPosts(userId);
    }

    @GetMapping(value = "/posts/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getPostsFromFollowedUsers(@PathVariable String userId) {
        return postService.getPostsFromFollowedUsers(userId);
    }

    @DeleteMapping(value = "/posts/{id}")
    public void deletePost(@PathVariable String id) {
        postService.deletePost(id);
    }

    @PostMapping(value = "/posts/{id}/{newUserId}")
    public void repost(@PathVariable String id, @PathVariable String newUserId) {
        postService.repost(id, newUserId);
    }

    @GetMapping(value="/mentions/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getMentions(@PathVariable String userId){
        return postService.getMentions(userId);
    }
}
