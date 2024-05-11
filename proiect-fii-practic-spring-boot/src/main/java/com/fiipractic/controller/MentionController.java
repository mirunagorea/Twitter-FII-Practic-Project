package com.fiipractic.controller;

import com.fiipractic.service.interfaces.MentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MentionController {
    private final MentionService mentionService;

    @Autowired
    public MentionController(MentionService mentionService) {
        this.mentionService = mentionService;
    }

    @PostMapping(value="/posts/{postId}/users/{mentionedUserId}")
    public void mentionUser(@PathVariable String mentionedUserId, @PathVariable String postId){
        mentionService.mentionUser(mentionedUserId, postId);
    }
}
