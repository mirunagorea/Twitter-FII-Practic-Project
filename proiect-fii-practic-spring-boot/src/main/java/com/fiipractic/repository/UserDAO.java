package com.fiipractic.repository;

import com.fiipractic.exception.UserNotFoundException;
import com.fiipractic.model.User;
import com.fiipractic.repository.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createUser(String firstName, String lastName, String username, String email, String password){
        return jdbcTemplate.update("INSERT INTO USER(FIRST_NAME, LAST_NAME, USERNAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?, ?)", firstName, lastName, username,email, password);
    }

    public int deleteUser(String id){
        return jdbcTemplate.update("DELETE FROM USER WHERE ID = ?", id);
    }

    public List<User> getAllUsers(){
        return jdbcTemplate.query("SELECT * FROM USER", new UserRowMapper());
    }

    public List<User> searchUsers(String name) {
            return jdbcTemplate.query("SELECT * FROM USER WHERE FIRST_NAME = ? OR LAST_NAME = ? OR USERNAME = ?", new UserRowMapper(), name, name, name);
    }

    public List<User> searchUsersByUsername(String username) {
        return jdbcTemplate.query("SELECT * FROM USER WHERE USERNAME = ?", new UserRowMapper(), username);
    }

    public List<User> searchUsersByEmail(String email){
        return jdbcTemplate.query("SELECT * FROM USER WHERE EMAIL = ?", new UserRowMapper(), email);
    }

    public User findByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE USERNAME = ?", new UserRowMapper(), username);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }
}
