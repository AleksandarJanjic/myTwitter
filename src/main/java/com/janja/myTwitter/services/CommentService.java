package com.janja.myTwitter.services;

import com.janja.myTwitter.beans.Comment;
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
public class CommentService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void postComment(int userid, int messageid, String content) {
        String sql = "insert into mytwitter.comment (userid, messageid, commenttime, content) values (?,?,?,?);";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String currentTime = format.format(date);
        jdbcTemplate.update(sql, userid, messageid, currentTime, content);
    }

    public ArrayList<Comment> getComments(int messageid) {
        String sql = "select * from mytwitter.comment where messageid = ? order by commenttime asc;";
        ArrayList<Comment> allComments = new ArrayList<Comment>();
        List<Map<String,Object>> comments = jdbcTemplate.queryForList(sql, messageid);
        for(Map row:comments) {
            Comment comment = new Comment();
            comment.setCommentid((int)row.get("commentid"));
            comment.setMessageid((int)row.get("messageid"));
            comment.setUserid((int)(row.get("userid")));
            comment.setContent((String)(row.get("content")));
            comment.setCommentTime((Timestamp)(row.get("commenttime")));
            allComments.add(comment);
        }
        return allComments;
    }
}
