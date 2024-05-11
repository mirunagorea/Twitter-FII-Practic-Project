package com.fiipractic.controller;

import com.fiipractic.model.Reply;
import com.fiipractic.service.interfaces.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReplyController {
    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping(value="/posts/{postId}/user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPostReply(@PathVariable String postId, @PathVariable String userId, @RequestBody Reply reply){
        if (reply.getIsPublic() == null) {
            System.out.printf("IS_PUBLIC IS NULL");
        }
        replyService.addPostReply(postId, userId, reply);
    }
}
