package com.fiipractic.service.interfaces;

import com.fiipractic.model.User;

public interface FollowService {
    void follow(String userId, String followedUserId);
    void unfollow(String userId, String followedUserId);
}
