package com.fiipractic.service.interfaces;

import com.fiipractic.model.Post;

import java.util.List;

public interface PostService {
    void addPost(Post post, String userId);

    List<Post> getOwnPosts(String userId);

    List<Post> getPostsFromFollowedUsers(String userId);

    void deletePost(String id);

    void repost(String id, String newUserId);

    List<Post> getMentions(String userId);
}
