package com.janja.myTwitter.services;

import com.janja.myTwitter.beans.Like;
import com.janja.myTwitter.beans.User;
import com.janja.myTwitter.dbaccess.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class LikeService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    User user;

    public void likeMessage(int messageID, int userID){
        boolean alreadyLiked = checkLiked(messageID, userID);
        if(alreadyLiked == true) {
            String sql = "DELETE FROM mytwitter.likes WHERE (messageid = ? AND userid = ?);";
            jdbcTemplate.update(sql, messageID, userID);
        } else {
            String sql = "INSERT INTO mytwitter.likes (messageid, userid, likeDate) VALUES (?, ?, ?)";
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = format.format(date);
            jdbcTemplate.update(sql, messageID, userID, currentTime);
        }
    }

    public boolean checkLiked(int messageID, int userID){
        String sql = "SELECT * FROM mytwitter.likes WHERE (messageid = ? AND userid = ?);";
        Like like = new Like();
        RowMapper<Like> likeRowMapper = new LikeMapper();
        List<Like> result = jdbcTemplate.query(sql, likeRowMapper, messageID, userID);
        if(result.isEmpty()) return false;
        else if (result.size() == 1) return true;
        else return false;
    }

    public ArrayList<User> getAllLikers(int messageID) {
        String sql = "SELECT userName FROM mytwitter.user u WHERE (SELECT userid FROM mytwitter.likes l WHERE (l.userid = u.userid) AND (messageid = ? ));";
        ArrayList<User> usersLiked = new ArrayList<User>();
        List<Map<String,Object>> likers = jdbcTemplate.queryForList(sql, messageID);
        for(Map row:likers) {
            User user = new User();
            user.setName((String)(row.get("userName")));
            usersLiked.add(user);
        }
        return usersLiked;
    }
}
