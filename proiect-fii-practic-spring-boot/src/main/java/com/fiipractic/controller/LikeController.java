package com.fiipractic.controller;

import com.fiipractic.service.interfaces.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/likes/{postId}/user/{userId}")
    public void likePost(@PathVariable String userId, @PathVariable String postId)
    {
        likeService.likePost(userId, postId);
    }

    @DeleteMapping("/likes/{postId}/user/{userId}")
    public void removeLike(@PathVariable String userId, @PathVariable String postId){
        likeService.removeLike(userId, postId);
    }
}
