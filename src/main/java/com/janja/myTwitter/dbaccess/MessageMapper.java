package com.janja.myTwitter.dbaccess;

import com.janja.myTwitter.beans.Message;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MessageMapper implements RowMapper<Message> {

    public Message mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Message message = new Message();
        message.setAuthor(resultSet.getInt("messageAuthor"));
        message.setContent(resultSet.getString("messageContent"));
        message.setMessageid(resultSet.getInt("messageid"));
        message.setMessageTime(resultSet.getTimestamp("messageDate"));
        message.setTotalLikes(resultSet.getLong("totalLikes"));
        message.setUsername(resultSet.getString("username"));
        return message;
    }
}
