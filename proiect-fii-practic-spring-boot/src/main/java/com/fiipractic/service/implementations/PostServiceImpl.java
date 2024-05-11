package com.fiipractic.service.implementations;

import com.fiipractic.exception.PostsNotFoundException;
import com.fiipractic.model.Post;
import com.fiipractic.repository.PostDAO;
import com.fiipractic.service.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostDAO postRepository;

    @Autowired
    public PostServiceImpl(PostDAO postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void addPost(Post post, String userId) {
        postRepository.createPost(userId, post.getMessage());
    }

    @Override
    public List<Post> getOwnPosts(String userId) {
        List<Post> ownPosts= postRepository.getOwnPosts(userId);
        if(ownPosts.isEmpty()){
            throw new PostsNotFoundException("No posts yet. You need to add posts first.");
        }
        return ownPosts;
    }

    @Override
    public List<Post> getPostsFromFollowedUsers(String userId) {
        List<Post> postsFromOtherUsers = postRepository.getPostsFromFollowedUsers(userId);
        if(postsFromOtherUsers.isEmpty()){
            throw new PostsNotFoundException("No posts in feed yet. You need to start following people first.");
        }
        return postsFromOtherUsers;
    }

    @Override
    public void deletePost(String id) {
        postRepository.deletePost(id);
    }

    @Override
    public void repost(String id, String newUserId) {
        postRepository.repost(id, newUserId);
    }

    @Override
    public List<Post> getMentions(String userId) {
        List<Post> mentionedInPosts = postRepository.getMentions(userId);
        if(mentionedInPosts.isEmpty())
            throw new PostsNotFoundException("You were not mentioned in any posts yet.");
        return mentionedInPosts;
    }
}
