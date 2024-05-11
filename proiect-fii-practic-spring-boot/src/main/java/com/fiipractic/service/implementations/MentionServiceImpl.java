package com.fiipractic.service.implementations;

import com.fiipractic.repository.MentionDAO;
import com.fiipractic.service.interfaces.MentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentionServiceImpl implements MentionService {
    private final MentionDAO mentionRepository;

    @Autowired
    public MentionServiceImpl(MentionDAO mentionRepository) {
        this.mentionRepository = mentionRepository;
    }

    @Override
    public void mentionUser(String mentionedUserId, String postId) {
        mentionRepository.mentionUser(mentionedUserId, postId);
    }
}
