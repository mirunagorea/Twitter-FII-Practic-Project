package com.fiipractic.controller;

import com.fiipractic.service.interfaces.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {
    private final FollowService followService;

    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping(value="/users/{userId}/follow/{followedUserId}")
    public void follow(@PathVariable String userId, @PathVariable String followedUserId){
        followService.follow(userId, followedUserId);
    }

    @DeleteMapping(value="/users/{userId}/follow/{followedUserId}")
    public void unfollow(@PathVariable String userId, @PathVariable String followedUserId){
        followService.unfollow(userId, followedUserId);
    }
}
