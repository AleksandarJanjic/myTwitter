package com.janja.myTwitter.REST;

import com.janja.myTwitter.beans.User;
import com.janja.myTwitter.services.LikeService;
import com.janja.myTwitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LikeMessage {

    @Autowired
    LikeService likeService;

    @Autowired
    UserService userService;

    @Autowired
    User user;

    @RequestMapping(value = "/message/likeMessage", method = RequestMethod.POST)
    public void likeMessage(
            HttpSession session,
            @RequestParam(value = "messageID", defaultValue = "") int messageid
    ){
        String username = (String) session.getAttribute("username");
        user = userService.getUserID(username);
        likeService.likeMessage(messageid, user.getId());
    }
}
