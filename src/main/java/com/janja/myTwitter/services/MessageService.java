package com.janja.myTwitter.services;

import com.janja.myTwitter.beans.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class MessageService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Message message;

    @Autowired
    UserService userService;

    public String postMessage(Message message) {
        String sql = "INSERT INTO mytwitter.message (messageAuthor, messageContent, messageDate) VALUES (?, ?, ?)";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String currentTime = format.format(date);
        jdbcTemplate.update(sql, message.getAuthor(), message.getContent(), currentTime);
        return "success";
    }

    public ArrayList<Message> getAllMessages() {
        ArrayList<Message> messages = new ArrayList<Message>();
        String sql = "SELECT m.messageid, m.messageContent, m.messageDate, m.messageAuthor, COUNT(l.userid) as totalLikes FROM mytwitter.message m LEFT JOIN mytwitter.likes l ON m.messageid = l.messageid GROUP BY messageid ORDER BY messageDate desc;";
        List<Map<String,Object>> allMessages = jdbcTemplate.queryForList(sql);
        for(Map row: allMessages) {
            Message message = new Message();
            message.setContent((String)(row.get("messageContent")));
            message.setUsername((userService.getUsernameById((int)row.get("messageAuthor"))));
            message.setMessageTime((Timestamp)(row.get("messageDate")));
            message.setTotalLikes((Long)(row.get("totalLikes")));
            message.setMessageid((int)(row.get("messageid")));
            messages.add(message);
        }
        return messages;
    }
}
