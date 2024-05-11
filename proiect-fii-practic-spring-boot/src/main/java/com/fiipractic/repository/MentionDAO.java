package com.fiipractic.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MentionDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int mentionUser(String mentionedUserId, String postId){
        return jdbcTemplate.update("INSERT INTO MENTION(USER_ID, POST_ID) VALUES(?, ?)", mentionedUserId, postId);
    }
}
