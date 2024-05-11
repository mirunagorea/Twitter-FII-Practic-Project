package com.fiipractic.repository;

import com.fiipractic.exception.PostsNotFoundException;
import com.fiipractic.model.Post;
import com.fiipractic.repository.mapper.PostRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PostDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createPost(String userId, String message) {
        return jdbcTemplate.update("INSERT INTO POST(USER_ID, MESSAGE) VALUES (?, ?)", userId, message);
    }

    public List<Post> getOwnPosts(String userId) {
        return jdbcTemplate.query("SELECT * FROM POST WHERE USER_ID = ?", new PostRowMapper(), userId);
    }

    public List<Post> getPostsFromFollowedUsers(String userId) {
        return jdbcTemplate.query("SELECT * FROM POST WHERE USER_ID IN(SELECT FOLLOWED_USER_ID FROM FOLLOW WHERE USER_ID = ?)", new PostRowMapper(), userId);
    }

    public int deletePost(String id) {
        return jdbcTemplate.update("DELETE FROM POST WHERE ID = ?", id);
    }

    public Post getOriginalPost(String id) {
        return jdbcTemplate.queryForObject("SELECT * FROM POST WHERE ID = ?", new PostRowMapper(), id);
    }

    public int repost(String id, String newUserId) {
        Post originalPost = getOriginalPost(id);
        String message = originalPost.getMessage();
        return jdbcTemplate.update("INSERT INTO POST(USER_ID, MESSAGE) VALUES(?, ?)", newUserId, message);
    }

    public List<Post> getMentions(String userId) {
        return jdbcTemplate.query("SELECT * FROM POST WHERE ID IN (SELECT POST_ID FROM MENTION WHERE USER_ID = ?)", new PostRowMapper(), userId);
    }
}
