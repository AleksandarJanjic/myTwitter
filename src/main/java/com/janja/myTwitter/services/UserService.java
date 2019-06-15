package com.janja.myTwitter.services;

import com.janja.myTwitter.beans.User;
import com.janja.myTwitter.dbaccess.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    User user;

    public List<User> getAllUsers() {
        String sql = "SELECT userid, userName, password FROM mytwitter.user";
        RowMapper<User> userRowMapper = new UserRowMapper();
        return this.jdbcTemplate.query(sql, userRowMapper);
    }

    public List<User> searchUserByName(String name) {
        String sql = "SELECT userid, userName, password FROM mytwitter.user WHERE userName LIKE '%?%'";
        RowMapper<User> userRowMapper = new UserRowMapper();
        return this.jdbcTemplate.query(sql, userRowMapper, name);
    }

    public String getUsernameById(int id) {
        String sql = "SELECT * FROM mytwitter.user WHERE userid = ?;";
        RowMapper<User> userRowMapper = new UserRowMapper();
        user = jdbcTemplate.queryForObject(sql, userRowMapper, id);
        String result = user.getName();
        return result;
    }

    public User getUserID(String name) {
        String sql = "SELECT * FROM mytwitter.user WHERE userName = ?";
        RowMapper<User> userRowMapper = new UserRowMapper();
        User result = new User();
        result = jdbcTemplate.queryForObject(sql, userRowMapper, name);
        return result;
    }

    public boolean getUser(String name) {
        String sql = "SELECT * FROM mytwitter.user WHERE userName = ?";
        User user = new User();
        RowMapper<User> userRowMapper = new UserRowMapper();
        List<User> result = jdbcTemplate.query(sql, userRowMapper, name);
        if(result.isEmpty()) return false;
        else if(result.size() == 1) return true;
        else return false;
    }

    public String createUser(User user) {
        boolean exists = getUser(user.getName());
        if(exists == true) {
            return "Account with that name already exists";
        } else {
            String sql = "INSERT INTO mytwitter.user (userName, password) VALUES (?, ?)";
            jdbcTemplate.update(sql, user.getName(), user.getPassword());
            return "Account creation success";
        }
    }

    public boolean checkUserCredentials(User user) {
        boolean passCheck;
        boolean exists = getUser(user.getName());
        if(exists == true) {
            String sql = "SELECT * FROM mytwitter.user WHERE userName = ?";
            User userInDB = new User();
            RowMapper<User> userRowMapper = new UserRowMapper();
            userInDB = jdbcTemplate.queryForObject(sql, userRowMapper, user.getName());
            if(userInDB.getPassword().equals(user.getPassword())) {
                passCheck = true;
            } else passCheck = false;
        } else return false;
        return passCheck;
    }
}
