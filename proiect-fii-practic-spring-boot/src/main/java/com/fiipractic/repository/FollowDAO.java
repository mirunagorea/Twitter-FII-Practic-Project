package com.fiipractic.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class FollowDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int follow(String userId, String followedUserId) {
        return jdbcTemplate.update("INSERT INTO FOLLOW(USER_ID, FOLLOWED_USER_ID) VALUES(?, ?)", userId, followedUserId);
    }

    public int unfollow(String userId, String followedUserId) {
        return jdbcTemplate.update("DELETE FROM FOLLOW WHERE USER_ID = ? AND FOLLOWED_USER_ID = ?", userId, followedUserId);
    }
}
