package com.janja.myTwitter.dbaccess;

import com.janja.myTwitter.beans.Like;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LikeMapper implements RowMapper<Like> {

    public Like mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Like like = new Like();
        like.setMessageid(resultSet.getInt("messageid"));
        like.setUserid(resultSet.getInt("userid"));
        like.setLikeDate(resultSet.getTimestamp("likeDate"));
        return like;
    }
}
