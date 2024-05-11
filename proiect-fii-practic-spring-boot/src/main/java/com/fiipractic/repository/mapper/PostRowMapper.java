package com.fiipractic.repository.mapper;

import com.fiipractic.model.Post;
import com.fiipractic.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setId(rs.getString("ID"));
        post.setMessage(rs.getString("MESSAGE"));
        post.setTimestamp(rs.getTimestamp("TIMESTAMP").getTime());

        User user = new User();
        user.setId(rs.getString("USER_ID"));

        post.setUser(user);

        return post;
    }
}
