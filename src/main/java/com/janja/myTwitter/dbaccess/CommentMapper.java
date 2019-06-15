package com.janja.myTwitter.dbaccess;

import com.janja.myTwitter.beans.Comment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CommentMapper implements RowMapper {

    @Override
    public Comment mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentid(resultSet.getInt("commentid"));
        comment.setMessageid(resultSet.getInt("messageid"));
        comment.setUserid(resultSet.getInt("userid"));
        comment.setContent(resultSet.getString("content"));
        comment.setCommentTime(resultSet.getTimestamp("commenttime"));
        return comment;
    }
}
