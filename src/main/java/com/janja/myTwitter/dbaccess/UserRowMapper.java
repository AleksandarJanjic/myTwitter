package com.janja.myTwitter.dbaccess;

import com.janja.myTwitter.beans.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setName(resultSet.getString("userName"));
        user.setPassword(resultSet.getString("password"));
        user.setId(resultSet.getInt("userid"));
        return user;
    }
}
