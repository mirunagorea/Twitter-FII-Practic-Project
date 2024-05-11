package com.fiipractic.service.interfaces;

import com.fiipractic.model.Like;

public interface LikeService {
    void likePost(String userId, String postId);

    void removeLike(String userId, String postId);
}
