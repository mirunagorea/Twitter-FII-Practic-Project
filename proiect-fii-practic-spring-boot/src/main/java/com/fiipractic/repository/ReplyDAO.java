package com.fiipractic.repository;

import com.fiipractic.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ReplyDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int addPostReply(String postId, String userId, Reply reply){
        int isPublicValue = reply.getIsPublic() ? 1 : 0;
        return jdbcTemplate.update("INSERT INTO REPLY(POST_ID, IS_PUBLIC, USER_ID, MESSAGE) VALUES(?, ?, ?, ?)", postId, isPublicValue, userId, reply.getMessage());
    }
}
