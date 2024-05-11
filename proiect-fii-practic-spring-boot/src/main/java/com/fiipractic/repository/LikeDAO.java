package com.fiipractic.repository;

import com.fiipractic.model.Like;
import com.fiipractic.model.Post;
import com.fiipractic.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class LikeDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int likePost(String userId, String postId) {
        return jdbcTemplate.update("INSERT INTO LIKES(USER_ID, POST_ID) VALUES(?, ?)", userId, postId);
    }

    public int removeLike(String userId, String postId) {
        return jdbcTemplate.update("DELETE FROM LIKES WHERE USER_ID = ? AND POST_ID = ?", userId, postId);
    }
}
