package com.fiipractic.service.interfaces;

import com.fiipractic.model.Post;
import com.fiipractic.model.Reply;

public interface ReplyService {
    void addPostReply(String postId, String userId, Reply reply);
}
