package com.fiipractic.service.implementations;

import com.fiipractic.repository.LikeDAO;
import com.fiipractic.service.interfaces.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    private final LikeDAO likeRepository;

    @Autowired
    public LikeServiceImpl(LikeDAO likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public void likePost(String userId, String postId) {
        likeRepository.likePost(userId, postId);
    }

    @Override
    public void removeLike(String userId, String postId) {
        likeRepository.removeLike(userId, postId);
    }
}
