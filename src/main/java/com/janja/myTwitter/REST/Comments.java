package com.janja.myTwitter.REST;

import com.janja.myTwitter.beans.Comment;
import com.janja.myTwitter.beans.User;
import com.janja.myTwitter.services.CommentService;
import com.janja.myTwitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
public class Comments {

    @Autowired
    User user;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/comment/postComment", method = RequestMethod.POST)
    public void postComment(
            HttpSession session,
            @RequestParam(value = "messageID") int messageid,
            @RequestParam(value = "content") String content
    ) {
        String username = (String) session.getAttribute("username");
        user = userService.getUserID(username);
        commentService.postComment(user.getId(),messageid, content);
    }

    @RequestMapping(value = "/comment/getComments", method = RequestMethod.POST)
    public ArrayList<Comment> getComments(
            @RequestParam(value = "messageID") int messageid
    ) {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        comments = commentService.getComments(messageid);
        return comments;
    }
}
