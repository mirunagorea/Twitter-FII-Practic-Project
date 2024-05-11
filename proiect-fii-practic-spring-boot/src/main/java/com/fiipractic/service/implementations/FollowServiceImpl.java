package com.fiipractic.service.implementations;

import com.fiipractic.repository.FollowDAO;
import com.fiipractic.service.interfaces.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {
    private final FollowDAO followRepository;

    @Autowired
    public FollowServiceImpl(FollowDAO followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public void follow(String userId, String followedUserId) {
        followRepository.follow(userId, followedUserId);
    }

    @Override
    public void unfollow(String userId, String followedUserId) {
        followRepository.unfollow(userId, followedUserId);
    }
}
