package com.fiipractic.service.implementations;

import com.fiipractic.model.Post;
import com.fiipractic.model.Reply;
import com.fiipractic.repository.ReplyDAO;
import com.fiipractic.service.interfaces.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {
    private final ReplyDAO replyRepository;

    @Autowired
    public ReplyServiceImpl(ReplyDAO replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Override
    public void addPostReply(String postId, String userId, Reply reply) {
        replyRepository.addPostReply(postId, userId, reply);
    }
}
